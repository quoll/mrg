/*
 * Copyright 2010 Paula Gearon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mulgara.mrg.vocab.uri;

import java.net.URI;

/**
 * A class for holding the VCard vocabulary.
 */
public class VCARD {

  /** The QName prefix for VCard */
  public static final String PREFIX = "vcard";

  /** The VCard namespace */
  public static final String BASE = "http://www.w3.org/2001/vcard-rdf/3.0#";

  public static final URI ORGPROPERTIES = URI.create(BASE + "ORGPROPERTIES" );
  public static final URI ADRTYPES = URI.create(BASE + "ADRTYPES" );
  public static final URI NPROPERTIES = URI.create(BASE + "NPROPERTIES" );
  public static final URI EMAILTYPES = URI.create(BASE + "EMAILTYPES" );
  public static final URI TELTYPES = URI.create(BASE + "TELTYPES" );
  public static final URI ADRPROPERTIES = URI.create(BASE + "ADRPROPERTIES" );
  public static final URI TZTYPES = URI.create(BASE + "TZTYPES" );
  public static final URI STREET = URI.create(BASE + "Street" );
  public static final URI AGENT = URI.create(BASE + "AGENT" );
  public static final URI SOURCE = URI.create(BASE + "SOURCE" );
  public static final URI LOGO = URI.create(BASE + "LOGO" );
  public static final URI BDAY = URI.create(BASE + "BDAY" );
  public static final URI REV = URI.create(BASE + "REV" );
  public static final URI SORT_STRING = URI.create(BASE + "SORT-STRING" );
  public static final URI ORGNAME = URI.create(BASE + "Orgname" );
  public static final URI CATEGORIES = URI.create(BASE + "CATEGORIES" );
  public static final URI N = URI.create(BASE + "N" );
  public static final URI PCODE = URI.create(BASE + "Pcode" );
  public static final URI PREFIX_ = URI.create(BASE + "Prefix" );
  public static final URI PHOTO = URI.create(BASE + "PHOTO" );
  public static final URI FN = URI.create(BASE + "FN" );
  public static final URI ORG = URI.create(BASE + "ORG" );
  public static final URI SUFFIX = URI.create(BASE + "Suffix" );
  public static final URI CLASS = URI.create(BASE + "CLASS" );
  public static final URI ADR = URI.create(BASE + "ADR" );
  public static final URI REGION = URI.create(BASE + "Region" );
  public static final URI GEO = URI.create(BASE + "GEO" );
  public static final URI EXTADD = URI.create(BASE + "Extadd" );
  public static final URI GROUP = URI.create(BASE + "GROUP" );
  public static final URI EMAIL = URI.create(BASE + "EMAIL" );
  public static final URI UID = URI.create(BASE + "UID" );
  public static final URI FAMILY = URI.create(BASE + "Family" );
  public static final URI TZ = URI.create(BASE + "TZ" );
  public static final URI NAME = URI.create(BASE + "NAME" );
  public static final URI ORGUNIT = URI.create(BASE + "Orgunit" );
  public static final URI COUNTRY = URI.create(BASE + "Country" );
  public static final URI SOUND = URI.create(BASE + "SOUND" );
  public static final URI TITLE = URI.create(BASE + "TITLE" );
  public static final URI NOTE = URI.create(BASE + "NOTE" );
  public static final URI MAILER = URI.create(BASE + "MAILER" );
  public static final URI OTHER = URI.create(BASE + "Other" );
  public static final URI LOCALITY = URI.create(BASE + "Locality" );
  public static final URI POBOX = URI.create(BASE + "Pobox" );
  public static final URI KEY = URI.create(BASE + "KEY" );
  public static final URI PRODID = URI.create(BASE + "PRODID" );
  public static final URI GIVEN = URI.create(BASE + "Given" );
  public static final URI LABEL = URI.create(BASE + "LABEL" );
  public static final URI TEL = URI.create(BASE + "TEL" );
  public static final URI NICKNAME = URI.create(BASE + "NICKNAME" );
  public static final URI ROLE = URI.create(BASE + "ROLE" );

}
