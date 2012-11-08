package br.com.caelum.guj.uri;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.jforum.util.bbcode.BBCode;
import net.jforum.util.bbcode.BBCodeHandler;
import net.jforum.util.preferences.ConfigKeys;
import net.jforum.util.preferences.SystemGlobals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BBCodeHandlerTest {
	
	private static BBCodeHandler handler;
	private String regexWithoutTags;
	private String regexWithTags;

	@BeforeClass
	public static void init() {
		SystemGlobals.setValue(ConfigKeys.CONFIG_DIR, "webapp/WEB-INF/config");
		handler = new BBCodeHandler().parse();
	}
	
	@Before
	public void initCode() {
		regexWithoutTags = handler.findByName("auto-url-follow").getRegex();
		regexWithTags = handler.findByName("simple-url-follow").getRegex();
	}
	
	@Test
	public void shouldReturnTrueForBigCompaniesUrl() {
		assertTrue("http://www.uol.com.br".matches(regexWithoutTags));
		assertTrue("http://www.apple.com".matches(regexWithoutTags));
		assertTrue("http://www.globo.com".matches(regexWithoutTags));
		assertTrue("http://www.oracle.com".matches(regexWithoutTags));
		assertTrue("http://sun.com".matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnFalseForUrlWithoutKeywords() {
		String url = "http://www.meusite.com.br/perfect-circle-eh-legal";
		assertFalse(url.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnTrueForUrlContainingCaelum() {
		String url = "http://www.meusite.com.br/caelum-eh-legal";
		String siteCaelum = "http://www.caelum.com.br";
		
		assertTrue(url.matches(regexWithoutTags));
		assertTrue(siteCaelum.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnTrueForUrlContainingMundoJ() {
		String url = "http://www.meusite.com.br/mundoj-eh-legal";
		String mundoJ = "http://www.mundoj.com.br";
		
		assertTrue(url.matches(regexWithoutTags));
		assertTrue(mundoJ.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnTrueForUrlContainingCasaDoCodigo() {
		String url = "http://www.casadocodigo.com.br/livro-muito-maroto";
		assertTrue(url.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnTrueForUrlContainingQCon() {
		String url = "http://www.somesite.com/soon-qcon-brazil";
		assertTrue(url.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnTrueForUrlConexaoJava() {
		String url = "http://www.somesite.com/evento-conexaojava-em-sp";
		assertTrue(url.matches(regexWithoutTags));
	}
	
	@Test
	public void shouldReturnMatchedUrl() {
		String url = "http://www.somesite.com/evento-conexaojava-em-sp";
		Pattern pattern = Pattern.compile(regexWithoutTags);
		Matcher matcher = pattern.matcher(url);
		
		assertTrue(matcher.matches());
		assertEquals(url, matcher.group(2));
	}
	
	@Test
	public void shouldReturnMatchedUrlIfSurroundedWithUrl() {
		String site = "www.caelum.com.br";
		String url = "[url]" + site + "[/url]";
		Pattern pattern = Pattern.compile(regexWithTags);
		Matcher matcher = pattern.matcher(url);
		
		assertTrue(matcher.matches());
		assertEquals(site, matcher.group(1));
		
		BBCode bb = handler.findByName("simple-url-follow");
		String text = url;
		text = text.replaceAll(bb.getRegex(), bb.getReplace());
		System.out.println(text);
	}
	
	@Test
	public void shouldReturnTrueForUrlBetweenTagsContainingKeywords() {
		String site = "http://www.somesite.com/evento-conexaojava-em-sp";
		String url = "[url]" + site + "[/url]";
		
		Pattern pattern = Pattern.compile(regexWithTags);
		Matcher matcher = pattern.matcher(url);
		
		assertTrue(matcher.matches());
		assertEquals(site, matcher.group(1));
	}
	
}
