/*
 * (c) Copyright 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 * [See end of file]
 */

package org.mulgara.jena.n3.turtle;

import org.mulgara.jena.n3.IRIResolver;
import org.mulgara.jena.n3.JenaURIException;
import org.mulgara.jena.shared.PrefixMapping;
import org.mulgara.jena.shared.impl.PrefixMappingImpl;
import org.mulgara.mrg.Bnode;
import org.mulgara.mrg.Literal;
import org.mulgara.mrg.Node;
import org.mulgara.mrg.Triple;
import org.mulgara.mrg.Uri;
import org.mulgara.mrg.vocab.OWL;
import org.mulgara.mrg.vocab.RDF;
import org.mulgara.mrg.vocab.uri.XSD;

import java.net.URI;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ParserBase
{
    /** Logging for this class. */
    private static final Logger logger = Logger.getLogger(ParserBase.class.getName());

    // Should be the same as ARQ ParserBase and Prologues.
    protected final Node XSD_TRUE   = new Literal("true", XSD.BOOLEAN) ;
    protected final Node XSD_FALSE  = new Literal("false", XSD.BOOLEAN) ;
    
    protected final Node nRDFtype       = RDF.TYPE;
    
    protected final Node nRDFnil        = RDF.NIL;
    protected final Node nRDFfirst      = RDF.FIRST;
    protected final Node nRDFrest       = RDF.REST;
    
    protected final Node nRDFsubject    = RDF.SUBJECT;
    protected final Node nRDFpredicate  = RDF.PREDICATE;
    protected final Node nRDFobject     = RDF.OBJECT;

    protected final String SWAP_NS      = "http://www.w3.org/2000/10/swap/" ;
    protected final String SWAP_LOG_NS  = "http://www.w3.org/2000/10/swap/log#" ;
    protected final Node nLogImplies    = Uri.create(SWAP_LOG_NS+"implies");
    
    protected final Node nOwlSameAs     = OWL.SAME_AS;
    
    protected boolean strictTurtle = true ;
    protected boolean skolomizedBNodes = true ; 
    
    public ParserBase() {}
    
    PrefixMapping prefixMapping = new PrefixMappingImpl() ;
    IRIResolver resolver = new IRIResolver() ;
    
    protected String getBaseURI()       { return resolver.getBaseIRI() ; }
    public void setBaseURI(String u)
    {
        resolver = new IRIResolver(u) ;
    }
    
    protected void setBase(String iriStr , int line, int column)
    {
        // Already resolved.
        setBaseURI(iriStr) ;
    }
    
    public PrefixMapping getPrefixMapping() { return prefixMapping ; }
    
    // label => bNode for construct templates patterns
    LabelToNodeMap bNodeLabels = new LabelToNodeMap() ;
    
    TurtleEventHandler handler = null ; 
    public void setEventHandler(TurtleEventHandler h) { handler = h ; }
    
    protected void emitTriple(int line, int col, Triple triple)
    {
        handler.triple(line, col, triple) ;
    }
    
    protected void startFormula(int line, int col)
    { handler.startFormula(line, col) ; }
    
    protected void endFormula(int line, int col)
    {handler.endFormula(line, col) ; }
    
    protected void setPrefix(int line, int col, String prefix, String uri)
    {
        prefixMapping.setNsPrefix(prefix, uri) ;
        handler.prefix(line, col, prefix, uri) ;
    }
    
    protected int makePositiveInteger(String lexicalForm)
    {
        if ( lexicalForm == null )
            return -1 ;
        
        return Integer.parseInt(lexicalForm) ;
    }
    
    protected Node createLiteralInteger(String lexicalForm)
    {
        return new Literal(lexicalForm, XSD.INTEGER) ;
    }
    
    protected Node createLiteralDouble(String lexicalForm)
    {
        return new Literal(lexicalForm, XSD.DOUBLE) ;
    }
    
    protected Node createLiteralDecimal(String lexicalForm)
    {
        return new Literal(lexicalForm, XSD.DECIMAL) ;
    }

    protected Node createLiteral(String lexicalForm, String langTag, Node datatype)
    {
        URI uri = (datatype!=null && datatype instanceof Uri) ? ((Uri)datatype).getURI() : null;
        return createLiteral(lexicalForm, langTag,  uri) ;
    }
    
    protected Node createLiteral(String lexicalForm, String langTag, String datatype) {
      URI datatypeURI = (datatype == null) ? null : URI.create(datatype);
      return createLiteral(lexicalForm, langTag, datatypeURI);
    }

    protected Node createLiteral(String lexicalForm, String langTag, URI datatypeURI)
    {
      return new Literal(lexicalForm, langTag, datatypeURI);
    }
    
    protected long integerValue(String s)
    {
        if ( s.startsWith("+") )
            s = s.substring(1) ;
        if ( s.startsWith("0x") )
        {
            // Hex
            s = s.substring(2) ;
            return Long.parseLong(s, 16) ;
        }
        return Long.parseLong(s) ;
    }
    
    protected double doubleValue(String s)
    {
        if ( s.startsWith("+") )
            s = s.substring(1) ;
        double valDouble = Double.parseDouble(s) ;
        return valDouble ; 
    }
    
    protected String stripQuotes(String s)
    {
        return s.substring(1,s.length()-1)  ;
    }
    
    protected String stripQuotes3(String s)
    {
        return s.substring(3,s.length()-3)  ;
    }
    
    protected String stripChars(String s, int n)
    {
        return s.substring(n, s.length())  ;
    }

    protected String resolveQuotedIRI(String iriStr ,int line, int column)
    {
        iriStr = stripQuotes(iriStr) ;
        return resolveIRI(iriStr, line, column) ;
    }
    
    protected String resolveIRI(String iriStr , int line, int column)
    {
        if ( isBNodeIRI(iriStr) )
            return iriStr ;
        
        if ( resolver != null )
            iriStr = _resolveIRI(iriStr, line, column) ;
        return iriStr ;
    }
    
    private String _resolveIRI(String iriStr , int line, int column)
    {
        try { iriStr = resolver.resolve(iriStr) ; }
        catch (JenaURIException ex) { throwParseException(ex.getMessage(), line, column) ; }
        return iriStr ;
    }
    
    protected String resolvePName(String qname, int line, int column)
    {
        String s = prefixMapping.expandPrefix(qname) ;
        if ( s == null || s.equals(qname) )
            throwParseException("Unresolved prefixed name: "+qname, line, column) ;
        return s ;
    }

    
    final static String bNodeLabelStart = "_:" ;
    
    protected Node createListNode() { return createBNode() ; }

    // Unlabelled bNode.
    protected Node createBNode() { return new Bnode() ; }
    
    //  Labelled bNode.
    protected Node createBNode(String label, int line, int column)
    { 
        return bNodeLabels.asNode(label) ;
    }
    protected Node createVariable(String s, int line, int column)
    {
        s = s.substring(1) ; // Drop the marker
        logger.log(Level.SEVERE, "Variables not supported: " + s);
        return createBNode(s, line, column);
    }
    
    protected Node createNode(String iri)
    {
        // Is it a bNode label? i.e. <_:xyz>
        if ( isBNodeIRI(iri) )
        {
            String s = iri.substring(bNodeLabelStart.length()) ;
            Node n = new Bnode();
            return n ;
        }
        return Uri.create(iri) ;
    }
    
    protected boolean isBNodeIRI(String iri)
    {
        return skolomizedBNodes && iri.startsWith(bNodeLabelStart) ;
    }
    

    
//    protected Node createNodeFromURI(String s, int line, int column)
//    {
//        s = stripQuotes(s) ;
//        String uriStr = s ;     // Mutated
//        
//        try {
//            uriStr = resolver.resolve(uriStr) ;
//        } catch (JenaURIException ex)
//        {
//            throw new TurtleParseException(exMsg(ex.getMessage(), line, column)) ;
//        }
//        return Node.createURI(uriStr) ;
//    }
    
    protected void throwParseException(String s , int line, int column)
    {
        throw new TurtleParseException(exMsg(s, line, column)) ;
    }
    
    protected String fixupPrefix(String prefix, int line, int column)
    {
        if ( prefix.endsWith(":") )
            prefix = prefix.substring(0, prefix.length()-1) ;
        return prefix ; 
    }
    
    // Utilities to remove escapes
    
    // Testing interface
    public static String unescapeStr(String s)
    { return unescape(s, '\\', false, 1, 1) ; }

//    public static String unescapeCodePoint(String s)
//    { return unescape(s, '\\', true, 1, 1) ; }
//
//    protected String unescapeCodePoint(String s, int line, int column)
//    { return unescape(s, '\\', true, line, column) ; }

    
    protected String unescapeStr(String s, int line, int column)
    { return unescape(s, '\\', false, line, column) ; }
    
    // Worker function
    private static String unescape(String s, char escape, boolean pointCodeOnly, int line, int column)
    {
        int i = s.indexOf(escape) ;
        
        if ( i == -1 )
            return s ;
        
        // Dump the initial part straight into the string buffer
        StringBuffer sb = new StringBuffer(s.substring(0,i)) ;
        int len = s.length() ;
        for ( ; i < len ; i++ )
        {
            char ch = s.charAt(i) ;
            // Keep line and column numbers.
            switch (ch)
            {
                case '\n': 
                case '\r':
                    line++ ;
                    column = 1 ;
                    break ;
                default:
                    column++ ;
                    break ;
            }

            if ( ch != escape )
            {
                sb.append(ch) ;
                continue ;
            }
                
            // Escape
            if ( i >= len-1 )
                throw new TurtleParseException(exMsg("Illegal escape at end of string", line, column)) ;
            char ch2 = s.charAt(i+1) ;
            column = column+1 ;
            i = i + 1 ;
            
            // \\u and \\U
            if ( ch2 == 'u' )
            {
                // i points to the \ so i+6 is next character
                if ( i+4 >= len )
                    throw new TurtleParseException(exMsg("\\u escape too short", line, column)) ;
                int x = hex(s, i+1, 4, line, column) ;
                sb.append((char)x) ;
                // Jump 1 2 3 4 -- already skipped \ and u
                i = i+4 ;
                column = column+4 ;
                continue ;
            }
            if ( ch2 == 'U' )
            {
                // i points to the \ so i+6 is next character
                if ( i+8 >= len )
                    throw new TurtleParseException(exMsg("\\U escape too short", line, column)) ;
                int x = hex(s, i+1, 8, line, column) ;
                sb.append((char)x) ;
                // Jump 1 2 3 4 5 6 7 8 -- already skipped \ and u
                i = i+8 ;
                column = column+8 ;
                continue ;
            }
            
            // Are we doing just point code escapes?
            // If so, \X-anything else is legal as a literal "\" and "X" 
            
            if ( pointCodeOnly )
            {
                sb.append('\\') ;
                sb.append(ch2) ;
                i = i + 1 ;
                continue ;
            }
            
            // Not just codepoints.  Must be a legal escape.
            char ch3 = 0 ;
            switch (ch2)
            {
                case 'n': ch3 = '\n' ;  break ; 
                case 't': ch3 = '\t' ;  break ;
                case 'r': ch3 = '\r' ;  break ;
                case 'b': ch3 = '\b' ;  break ;
                case 'f': ch3 = '\f' ;  break ;
                case '\'': ch3 = '\'' ; break ;
                case '\"': ch3 = '\"' ; break ;
                case '\\': ch3 = '\\' ; break ;
                default:
                    throw new TurtleParseException(exMsg("Unknown escape: \\"+ch2, line, column)) ;
            }
            sb.append(ch3) ;
        }
        return sb.toString() ;
    }

    // Line and column that started the escape
    static private int hex(String s, int i, int len, int line, int column)
    {
//        if ( i+len >= s.length() )
//        {
//            
//        }
        int x = 0 ;
        for ( int j = i ; j < i+len ; j++ )
        {
           char ch = s.charAt(j) ;
           column++ ;
           int k = 0  ;
           switch (ch)
           {
               case '0': k = 0 ; break ; 
               case '1': k = 1 ; break ;
               case '2': k = 2 ; break ;
               case '3': k = 3 ; break ;
               case '4': k = 4 ; break ;
               case '5': k = 5 ; break ;
               case '6': k = 6 ; break ;
               case '7': k = 7 ; break ;
               case '8': k = 8 ; break ;
               case '9': k = 9 ; break ;
               case 'A': case 'a': k = 10 ; break ;
               case 'B': case 'b': k = 11 ; break ;
               case 'C': case 'c': k = 12 ; break ;
               case 'D': case 'd': k = 13 ; break ;
               case 'E': case 'e': k = 14 ; break ;
               case 'F': case 'f': k = 15 ; break ;
               default:
                   throw new TurtleParseException(exMsg("Illegal hex escape: "+ch, line, column)) ;
           }
           x = (x<<4)+k ;
        }
        return x ;
    }
    
    protected static String exMsg(String msg, int line, int column)
    {
        return "Line " + line + ", column " + column + ": " + msg ;
    }
}

/*
 * (c) Copyright 2007, 2008, 2009 Hewlett-Packard Development Company, LP
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. The name of the author may not be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */