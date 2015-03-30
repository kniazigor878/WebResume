package by.iharkaratkou;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;

public class FlagDownloader {

	public static void main(String[] args) {

		try {
			HashMap<String, String> ctr = new HashMap<String, String>();
			ctr.put("1", "Afghanistan");
			ctr.put("2", "Albania");
			ctr.put("3", "Algeria");
			ctr.put("4", "Andorra");
			ctr.put("5", "Angola");
			ctr.put("6", "Antigua and Barbuda");
			ctr.put("7", "Argentina");
			ctr.put("8", "Armenia");
			ctr.put("9", "Australia");
			ctr.put("10", "Austria");
			ctr.put("11", "Azerbaijan");
			ctr.put("12", "Bahamas");
			ctr.put("13", "Bahrain");
			ctr.put("14", "Bangladesh");
			ctr.put("15", "Barbados");
			ctr.put("16", "Belarus");
			ctr.put("17", "Belgium");
			ctr.put("18", "Belize");
			ctr.put("19", "Benin");
			ctr.put("20", "Bhutan");
			ctr.put("21", "Bolivia");
			ctr.put("22", "Bosnia and Herzegovina");
			ctr.put("23", "Botswana");
			ctr.put("24", "Brazil");
			ctr.put("25", "Brunei");
			ctr.put("26", "Bulgaria");
			ctr.put("27", "Burkina Faso");
			ctr.put("28", "Burma");
			ctr.put("29", "Burundi");
			ctr.put("43", "C�te d'Ivoire");
			ctr.put("30", "Cambodia");
			ctr.put("31", "Cameroon");
			ctr.put("32", "Canada");
			ctr.put("33", "Cape Verde");
			ctr.put("34", "Central African Republic");
			ctr.put("35", "Chad");
			ctr.put("36", "Chile");
			ctr.put("37", "China");
			ctr.put("38", "Colombia");
			ctr.put("39", "Comoros");
			ctr.put("40", "Congo Rep");
			ctr.put("41", "Congo Dem");
			ctr.put("42", "Costa Rica");
			ctr.put("44", "Croatia");
			ctr.put("45", "Cuba");
			ctr.put("46", "Cyprus");
			ctr.put("47", "Czech Republic");
			ctr.put("48", "Denmark");
			ctr.put("49", "Djibouti");
			ctr.put("50", "Dominica");
			ctr.put("51", "Dominican Republic");
			ctr.put("52", "East Timor");
			ctr.put("53", "Ecuador");
			ctr.put("54", "Egypt");
			ctr.put("55", "El Salvador");
			ctr.put("200", "England");
			ctr.put("56", "Equatorial Guinea");
			ctr.put("57", "Eritrea");
			ctr.put("58", "Estonia");
			ctr.put("59", "Ethiopia");
			ctr.put("194", "EU");
			ctr.put("201", "Faroese");
			ctr.put("60", "Fiji");
			ctr.put("61", "Finland");
			ctr.put("62", "France");
			ctr.put("63", "Gabon");
			ctr.put("64", "Gambia");
			ctr.put("65", "Georgia");
			ctr.put("66", "Germany");
			ctr.put("67", "Ghana");
			ctr.put("68", "Greece");
			ctr.put("69", "Grenada");
			ctr.put("70", "Guatemala");
			ctr.put("71", "Guinea");
			ctr.put("72", "Guinea-Bissau");
			ctr.put("73", "Guyana");
			ctr.put("74", "Haiti");
			ctr.put("75", "Honduras");
			ctr.put("196", "Hong Kong");
			ctr.put("76", "Hungary");
			ctr.put("77", "Iceland");
			ctr.put("78", "India");
			ctr.put("79", "Indonesia");
			ctr.put("80", "Iran");
			ctr.put("81", "Iraq");
			ctr.put("82", "Ireland");
			ctr.put("205", "Isle of Man");
			ctr.put("83", "Israel");
			ctr.put("84", "Italy");
			ctr.put("85", "Jamaica");
			ctr.put("86", "Japan");
			ctr.put("87", "Jordan");
			ctr.put("88", "Kazakhstan");
			ctr.put("89", "Kenya");
			ctr.put("90", "Kiribati");
			ctr.put("91", "Korea, North");
			ctr.put("92", "Korea, South");
			ctr.put("199", "Kosovo");
			ctr.put("93", "Kuwait");
			ctr.put("94", "Kyrgyzstan");
			ctr.put("95", "Laos");
			ctr.put("96", "Latvia");
			ctr.put("97", "Lebanon");
			ctr.put("98", "Lesotho");
			ctr.put("99", "Liberia");
			ctr.put("100", "Libya");
			ctr.put("101", "Liechtenstein");
			ctr.put("102", "Lithuania");
			ctr.put("103", "Luxembourg");
			ctr.put("197", "Macau");
			ctr.put("104", "Macedonia");
			ctr.put("105", "Madagascar");
			ctr.put("106", "Malawi");
			ctr.put("107", "Malaysia");
			ctr.put("108", "Maldives");
			ctr.put("109", "Mali");
			ctr.put("110", "Malta");
			ctr.put("111", "Marshall Islands");
			ctr.put("112", "Mauritania");
			ctr.put("113", "Mauritius");
			ctr.put("114", "Mexico");
			ctr.put("115", "Micronesia");
			ctr.put("116", "Moldova");
			ctr.put("117", "Monaco");
			ctr.put("118", "Mongolia");
			ctr.put("119", "Montenegro");
			ctr.put("120", "Morocco");
			ctr.put("121", "Mozambique");
			ctr.put("122", "Namibia");
			ctr.put("123", "Nauru");
			ctr.put("124", "Nepal");
			ctr.put("125", "Netherlands");
			ctr.put("126", "New Zealand");
			ctr.put("127", "Nicaragua");
			ctr.put("128", "Niger");
			ctr.put("129", "Nigeria");
			ctr.put("130", "Norway");
			ctr.put("131", "Oman");
			ctr.put("132", "Pakistan");
			ctr.put("133", "Palau");
			ctr.put("134", "Panama");
			ctr.put("135", "Papua New Guinea");
			ctr.put("136", "Paraguay");
			ctr.put("137", "Peru");
			ctr.put("138", "Philippines");
			ctr.put("139", "Poland");
			ctr.put("140", "Portugal");
			ctr.put("203", "Puerto Rico");
			ctr.put("141", "Qatar");
			ctr.put("142", "Romania");
			ctr.put("143", "Russia");
			ctr.put("144", "Rwanda");
			ctr.put("150", "S�o Tom� and Pr�ncipe");
			ctr.put("145", "Saint Kitts and Nevis");
			ctr.put("146", "Saint Lucia");
			ctr.put("147", "Saint Vincent and the Grenadines");
			ctr.put("148", "Samoa");
			ctr.put("149", "San Marino");
			ctr.put("151", "Saudi Arabia");
			ctr.put("195", "Scotland");
			ctr.put("152", "Senegal");
			ctr.put("153", "Serbia");
			ctr.put("154", "Seychelles");
			ctr.put("155", "Sierra Leone");
			ctr.put("156", "Singapore");
			ctr.put("157", "Slovakia");
			ctr.put("158", "Slovenia");
			ctr.put("159", "Solomon Islands");
			ctr.put("160", "Somalia");
			ctr.put("161", "South Africa");
			ctr.put("162", "Spain");
			ctr.put("163", "Sri Lanka");
			ctr.put("164", "Sudan");
			ctr.put("165", "Suriname");
			ctr.put("166", "Swaziland");
			ctr.put("167", "Sweden");
			ctr.put("168", "Switzerland");
			ctr.put("169", "Syria");
			ctr.put("198", "Taiwan");
			ctr.put("170", "Tajikistan");
			ctr.put("171", "Tanzania");
			ctr.put("172", "Thailand");
			ctr.put("173", "Togo");
			ctr.put("174", "Tonga");
			ctr.put("175", "Trinidad and Tobago");
			ctr.put("176", "Tunisia");
			ctr.put("177", "Turkey");
			ctr.put("178", "Turkmenistan");
			ctr.put("179", "Tuvalu");
			ctr.put("180", "Uganda");
			ctr.put("181", "Ukraine");
			ctr.put("182", "United Arab Emirates");
			ctr.put("183", "United Kingdom");
			ctr.put("184", "United States of America");
			ctr.put("185", "Uruguay");
			ctr.put("204", "USSR");
			ctr.put("186", "Uzbekistan");
			ctr.put("187", "Vanuatu");
			ctr.put("188", "Vatican City");
			ctr.put("189", "Venezuela");
			ctr.put("190", "Vietnam");
			ctr.put("202", "Wales");
			ctr.put("191", "Yemen");
			ctr.put("192", "Zambia");
			ctr.put("193", "Zimbabwe");

			for (Integer j = 1; j < ctr.size() + 1; j++) {

				URL url = new URL(
						"http://flags.redpixart.com/download/?onlyflag=1&country=" + j.toString());

				InputStream in = new BufferedInputStream(url.openStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
				out.close();
				in.close();
				byte[] response = out.toByteArray();

				FileOutputStream fos = new FileOutputStream(
						"d:/Eclipse_Workspace_luna/upload/countries/" + ctr.get(j.toString()) + ".gif");
				fos.write(response);
				fos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}