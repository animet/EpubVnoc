package Vnoc.HTML;

import java.util.ArrayList;


//http://de.selfhtml.org/html/referenz/zeichen.htm
public class SignConverter {

	public static final String NO_HTML_SIGN_CONVERT_BEGIN = "&<noHtml;";
	public static final String NO_HTML_SIGN_CONVERT_END = "&noHtml>;";
	
	public static String getHtmlFormatedText(String str)
	{
		ArrayList<NoHtmlConvertSection> sections = getNoHtmlConvertSections(str);
		for(int i = 0; i < str.length(); i++)
		{
			if (!isNoHtmlConvertSection(sections, i))
			{
				if (str.charAt(i) == '"')
				    str = str.substring(0, i) + "&quot;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '&')
				    str = str.substring(0, i) + "&amp;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '<')
				    str = str.substring(0, i) + "&lt;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '>')
				    str = str.substring(0, i) + "&gt;" + str.substring(i + 1, str.length());
				//else if (str.charAt(i) == ' ')
				  //  str = str.substring(0, i) + "&nbsp;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¡')
				    str = str.substring(0, i) + "&iexcl;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¢')
				    str = str.substring(0, i) + "&cent;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '£')
				    str = str.substring(0, i) + "&pound;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¤')
				    str = str.substring(0, i) + "&curren;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¥')
				    str = str.substring(0, i) + "&yen;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¦')
				    str = str.substring(0, i) + "&brvbar;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '§')
				    str = str.substring(0, i) + "&sect;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¨')
				    str = str.substring(0, i) + "&uml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '©')
				    str = str.substring(0, i) + "&copy;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ª')
				    str = str.substring(0, i) + "&ordf;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '«')
				    str = str.substring(0, i) + "&laquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¬')
				    str = str.substring(0, i) + "&not;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '­')
				    str = str.substring(0, i) + "&shy;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '®')
				    str = str.substring(0, i) + "&reg;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¯')
				    str = str.substring(0, i) + "&macr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '°')
				    str = str.substring(0, i) + "&deg;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '±')
				    str = str.substring(0, i) + "&plusmn;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '²')
				    str = str.substring(0, i) + "&sup2;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '³')
				    str = str.substring(0, i) + "&sup3;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '´')
				    str = str.substring(0, i) + "&acute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'µ')
				    str = str.substring(0, i) + "&micro;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¶')
				    str = str.substring(0, i) + "&para;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '·')
				    str = str.substring(0, i) + "&middot;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¸')
				    str = str.substring(0, i) + "&cedil;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¹')
				    str = str.substring(0, i) + "&sup1;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'º')
				    str = str.substring(0, i) + "&ordm;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '»')
				    str = str.substring(0, i) + "&raquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¼')
				    str = str.substring(0, i) + "&frac14;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '½')
				    str = str.substring(0, i) + "&frac12;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¾')
				    str = str.substring(0, i) + "&frac34;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '¿')
				    str = str.substring(0, i) + "&iquest;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'À')
				    str = str.substring(0, i) + "&Agrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Á')
				    str = str.substring(0, i) + "&Aacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Â')
				    str = str.substring(0, i) + "&Acirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ã')
				    str = str.substring(0, i) + "&Atilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ä')
				    str = str.substring(0, i) + "&Auml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Å')
				    str = str.substring(0, i) + "&Aring;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Æ')
				    str = str.substring(0, i) + "&AElig;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ç')
				    str = str.substring(0, i) + "&Ccedil;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'È')
				    str = str.substring(0, i) + "&Egrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'É')
				    str = str.substring(0, i) + "&Eacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ê')
				    str = str.substring(0, i) + "&Ecirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ë')
				    str = str.substring(0, i) + "&Euml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ì')
				    str = str.substring(0, i) + "&Igrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Í')
				    str = str.substring(0, i) + "&Iacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Î')
				    str = str.substring(0, i) + "&Icirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ï')
				    str = str.substring(0, i) + "&Iuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ð')
				    str = str.substring(0, i) + "&ETH;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ñ')
				    str = str.substring(0, i) + "&Ntilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ò')
				    str = str.substring(0, i) + "&Ograve;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ó')
				    str = str.substring(0, i) + "&Oacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ô')
				    str = str.substring(0, i) + "&Ocirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Õ')
				    str = str.substring(0, i) + "&Otilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ö')
				    str = str.substring(0, i) + "&Ouml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '×')
				    str = str.substring(0, i) + "&times;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ø')
				    str = str.substring(0, i) + "&Oslash;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ù')
				    str = str.substring(0, i) + "&Ugrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ú')
				    str = str.substring(0, i) + "&Uacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Û')
				    str = str.substring(0, i) + "&Ucirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ü')
				    str = str.substring(0, i) + "&Uuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ý')
				    str = str.substring(0, i) + "&Yacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Þ')
				    str = str.substring(0, i) + "&THORN;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ß')
				    str = str.substring(0, i) + "&szlig;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'à')
				    str = str.substring(0, i) + "&agrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'á')
				    str = str.substring(0, i) + "&aacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'â')
				    str = str.substring(0, i) + "&acirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ã')
				    str = str.substring(0, i) + "&atilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ä')
				    str = str.substring(0, i) + "&auml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'å')
				    str = str.substring(0, i) + "&aring;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'æ')
				    str = str.substring(0, i) + "&aelig;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ç')
				    str = str.substring(0, i) + "&ccedil;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'è')
				    str = str.substring(0, i) + "&egrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'é')
				    str = str.substring(0, i) + "&eacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ê')
				    str = str.substring(0, i) + "&ecirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ë')
				    str = str.substring(0, i) + "&euml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ì')
				    str = str.substring(0, i) + "&igrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'í')
				    str = str.substring(0, i) + "&iacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'î')
				    str = str.substring(0, i) + "&icirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ï')
				    str = str.substring(0, i) + "&iuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ð')
				    str = str.substring(0, i) + "&eth;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ñ')
				    str = str.substring(0, i) + "&ntilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ò')
				    str = str.substring(0, i) + "&ograve;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ó')
				    str = str.substring(0, i) + "&oacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ô')
				    str = str.substring(0, i) + "&ocirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'õ')
				    str = str.substring(0, i) + "&otilde;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ö')
				    str = str.substring(0, i) + "&ouml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '÷')
				    str = str.substring(0, i) + "&divide;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ø')
				    str = str.substring(0, i) + "&oslash;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ù')
				    str = str.substring(0, i) + "&ugrave;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ú')
				    str = str.substring(0, i) + "&uacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'û')
				    str = str.substring(0, i) + "&ucirc;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ü')
				    str = str.substring(0, i) + "&uuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ý')
				    str = str.substring(0, i) + "&yacute;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'þ')
				    str = str.substring(0, i) + "&thorn;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ÿ')
				    str = str.substring(0, i) + "&yuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Α')
				    str = str.substring(0, i) + "&Alpha;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'α')
				    str = str.substring(0, i) + "&alpha;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Β')
				    str = str.substring(0, i) + "&Beta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'β')
				    str = str.substring(0, i) + "&beta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Γ')
				    str = str.substring(0, i) + "&Gamma;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'γ')
				    str = str.substring(0, i) + "&gamma;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Δ')
				    str = str.substring(0, i) + "&Delta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'δ')
				    str = str.substring(0, i) + "&delta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ε')
				    str = str.substring(0, i) + "&Epsilon;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ε')
				    str = str.substring(0, i) + "&epsilon;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ζ')
				    str = str.substring(0, i) + "&Zeta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ζ')
				    str = str.substring(0, i) + "&zeta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Η')
				    str = str.substring(0, i) + "&Eta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'η')
				    str = str.substring(0, i) + "&eta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Θ')
				    str = str.substring(0, i) + "&Theta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'θ')
				    str = str.substring(0, i) + "&theta;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ι')
				    str = str.substring(0, i) + "&Iota;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ι')
				    str = str.substring(0, i) + "&iota;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Κ')
				    str = str.substring(0, i) + "&Kappa;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'κ')
				    str = str.substring(0, i) + "&kappa;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Λ')
				    str = str.substring(0, i) + "&Lambda;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'λ')
				    str = str.substring(0, i) + "&lambda;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Μ')
				    str = str.substring(0, i) + "&Mu;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'μ')
				    str = str.substring(0, i) + "&mu;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ν')
				    str = str.substring(0, i) + "&Nu;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ν')
				    str = str.substring(0, i) + "&nu;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ξ')
				    str = str.substring(0, i) + "&Xi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ξ')
				    str = str.substring(0, i) + "&xi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ο')
				    str = str.substring(0, i) + "&Omicron;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ο')
				    str = str.substring(0, i) + "&omicron;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Π')
				    str = str.substring(0, i) + "&Pi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'π')
				    str = str.substring(0, i) + "&pi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ρ')
				    str = str.substring(0, i) + "&Rho;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ρ')
				    str = str.substring(0, i) + "&rho;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Σ')
				    str = str.substring(0, i) + "&Sigma;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ς')
				    str = str.substring(0, i) + "&sigmaf;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'σ')
				    str = str.substring(0, i) + "&sigma;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Τ')
				    str = str.substring(0, i) + "&Tau;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'τ')
				    str = str.substring(0, i) + "&tau;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Υ')
				    str = str.substring(0, i) + "&Upsilon;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'υ')
				    str = str.substring(0, i) + "&upsilon;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Φ')
				    str = str.substring(0, i) + "&Phi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'φ')
				    str = str.substring(0, i) + "&phi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Χ')
				    str = str.substring(0, i) + "&Chi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'χ')
				    str = str.substring(0, i) + "&chi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ψ')
				    str = str.substring(0, i) + "&Psi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ψ')
				    str = str.substring(0, i) + "&psi;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ω')
				    str = str.substring(0, i) + "&Omega;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ω')
				    str = str.substring(0, i) + "&omega;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ϑ')
				    str = str.substring(0, i) + "&thetasym;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ϒ')
				    str = str.substring(0, i) + "&upsih;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ϖ')
				    str = str.substring(0, i) + "&piv;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∀')
				    str = str.substring(0, i) + "&forall;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∂')
				    str = str.substring(0, i) + "&part;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∃')
				    str = str.substring(0, i) + "&exist;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∅')
				    str = str.substring(0, i) + "&empty;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∇')
				    str = str.substring(0, i) + "&nabla;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∈')
				    str = str.substring(0, i) + "&isin;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∉')
				    str = str.substring(0, i) + "&notin;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∋')
				    str = str.substring(0, i) + "&ni;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∝')
				    str = str.substring(0, i) + "&prod;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∑')
				    str = str.substring(0, i) + "&sum;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '−')
				    str = str.substring(0, i) + "&minus;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∗')
				    str = str.substring(0, i) + "&lowast;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '√')
				    str = str.substring(0, i) + "&radic;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∝')
				    str = str.substring(0, i) + "&prop;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∞')
				    str = str.substring(0, i) + "&infin;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∠')
				    str = str.substring(0, i) + "&ang;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∧')
				    str = str.substring(0, i) + "&and;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∨')
				    str = str.substring(0, i) + "&or;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∩')
				    str = str.substring(0, i) + "&cap;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∪')
				    str = str.substring(0, i) + "&cup;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∫')
				    str = str.substring(0, i) + "&int;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∴')
				    str = str.substring(0, i) + "&there4;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '∼')
				    str = str.substring(0, i) + "&sim;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≅')
				    str = str.substring(0, i) + "&cong;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≈')
				    str = str.substring(0, i) + "&asymp;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≠')
				    str = str.substring(0, i) + "&ne;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≡')
				    str = str.substring(0, i) + "&equiv;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≤')
				    str = str.substring(0, i) + "&le;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '≥')
				    str = str.substring(0, i) + "&ge;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊂')
				    str = str.substring(0, i) + "&sub;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊃')
				    str = str.substring(0, i) + "&sup;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊄')
				    str = str.substring(0, i) + "&nsub;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊆')
				    str = str.substring(0, i) + "&sube;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊇')
				    str = str.substring(0, i) + "&supe;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊕')
				    str = str.substring(0, i) + "&oplus;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊗')
				    str = str.substring(0, i) + "&otimes;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⊥')
				    str = str.substring(0, i) + "&perp;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⋅')
				    str = str.substring(0, i) + "&sdot;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '◊')
				    str = str.substring(0, i) + "&loz;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '←')
				    str = str.substring(0, i) + "&larr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '↑')
				    str = str.substring(0, i) + "&uarr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '→')
				    str = str.substring(0, i) + "&rarr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '↓')
				    str = str.substring(0, i) + "&darr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '↔')
				    str = str.substring(0, i) + "&harr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '↵')
				    str = str.substring(0, i) + "&crarr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⇐')
				    str = str.substring(0, i) + "&lArr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⇑')
				    str = str.substring(0, i) + "&uArr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⇒')
				    str = str.substring(0, i) + "&rArr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⇓')
				    str = str.substring(0, i) + "&dArr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⇔')
				    str = str.substring(0, i) + "&hArr;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '•')
				    str = str.substring(0, i) + "&bull;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '′')
				    str = str.substring(0, i) + "&prime;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '″')
				    str = str.substring(0, i) + "&Prime;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‾')
				    str = str.substring(0, i) + "&oline;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '⁄')
				    str = str.substring(0, i) + "&frasl;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '℘')
				    str = str.substring(0, i) + "&weierp;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ℑ')
				    str = str.substring(0, i) + "&image;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ℜ')
				    str = str.substring(0, i) + "&real;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '™')
				    str = str.substring(0, i) + "&trade;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '€')
				    str = str.substring(0, i) + "&euro;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ℵ')
				    str = str.substring(0, i) + "&alefsym;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '♠')
				    str = str.substring(0, i) + "&spades;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '♣')
				    str = str.substring(0, i) + "&clubs;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '♥')
				    str = str.substring(0, i) + "&hearts;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '♦')
				    str = str.substring(0, i) + "&diams;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Œ')
				    str = str.substring(0, i) + "&OElig;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'œ')
				    str = str.substring(0, i) + "&oelig;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Š')
				    str = str.substring(0, i) + "&Scaron;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'š')
				    str = str.substring(0, i) + "&scaron;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'Ÿ')
				    str = str.substring(0, i) + "&Yuml;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ƒ')
				    str = str.substring(0, i) + "&fnof;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '–')
				    str = str.substring(0, i) + "&ndash;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '—')
				    str = str.substring(0, i) + "&mdash;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‘')
				    str = str.substring(0, i) + "&lsquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '’')
				    str = str.substring(0, i) + "&rsquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‚')
				    str = str.substring(0, i) + "&sbquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '“')
				    str = str.substring(0, i) + "&ldquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '”')
				    str = str.substring(0, i) + "&rdquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '„')
				    str = str.substring(0, i) + "&bdquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '†')
				    str = str.substring(0, i) + "&dagger;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‡')
				    str = str.substring(0, i) + "&Dagger;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '…')
				    str = str.substring(0, i) + "&hellip;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‰')
				    str = str.substring(0, i) + "&permil;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '‹')
				    str = str.substring(0, i) + "&lsaquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '›')
				    str = str.substring(0, i) + "&rsaquo;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == 'ˆ')
				    str = str.substring(0, i) + "&circ;" + str.substring(i + 1, str.length());
				else if (str.charAt(i) == '˜')
				    str = str.substring(0, i) + "&tilde;" + str.substring(i + 1, str.length());
			}
		}
		return str;
	}
	
	private static ArrayList<NoHtmlConvertSection> getNoHtmlConvertSections(String str)
	{
		
		int noHtmlConvertBeginLength = NO_HTML_SIGN_CONVERT_BEGIN.length();
		int noHtmlConvertEndLength = NO_HTML_SIGN_CONVERT_END.length();
		ArrayList<NoHtmlConvertSection> sections = new ArrayList<NoHtmlConvertSection>();
		boolean startFound = false;
		int startIndex = -1;
		for(int i = 0; i < str.length() - noHtmlConvertEndLength + 1; i++)
		{
			
			if (str.substring(i, i + noHtmlConvertBeginLength).equals(NO_HTML_SIGN_CONVERT_BEGIN))
			{
				startFound = true;
				startIndex = i;
			}
			if (startFound && str.substring(i, i + noHtmlConvertEndLength).equals(NO_HTML_SIGN_CONVERT_END))
			{
				sections.add(new NoHtmlConvertSection(startIndex, i + noHtmlConvertEndLength));
				startFound = false;
			}
		}
		return sections;
	}
	
	private static boolean isNoHtmlConvertSection(ArrayList<NoHtmlConvertSection> sections, int strIndex)
	{
		boolean isIndexInNoHtmlSection = false;
		for(NoHtmlConvertSection noHtml : sections)
		{
			if (noHtml.StartIndex <= strIndex && noHtml.EndIndex >= strIndex)
			{
				isIndexInNoHtmlSection = true;
				break;
			}
		}
		return isIndexInNoHtmlSection;
	}
}
