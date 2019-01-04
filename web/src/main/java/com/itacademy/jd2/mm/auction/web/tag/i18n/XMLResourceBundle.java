package com.itacademy.jd2.mm.auction.web.tag.i18n;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

class XMLResourceBundle extends ResourceBundle {
	private Properties props;

	XMLResourceBundle(InputStream stream) throws IOException {
		props = new Properties();
		props.loadFromXML(stream);
	}

	protected Object handleGetObject(String key) {
		return props.getProperty(key);
	}

	public Enumeration<String> getKeys() {
		Set<String> handleKeys = props.stringPropertyNames();
		return Collections.enumeration(handleKeys);
	}
}