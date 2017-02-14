/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.ServletContext;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lars Grefer
 */
public class ServletContextConfigurerTest {

	private ServletContextConfigurer servletContextConfigurer;
	private ServletContext servletContext;

	@Before
	public void setUp() throws Exception {
		servletContext = new MockServletContext();

		servletContextConfigurer = new ServletContextConfigurer(servletContext, "") {
			@Override
			public void configure() {
			}
		};
	}

	@Test
	public void setInitParameterString() throws Exception {
		servletContextConfigurer.setInitParameterString("foo", "bar");

		assertThat(servletContext.getInitParameter("foo")).isEqualTo("bar");
	}

	@Test
	public void setInitParameterBoolean() throws Exception {
		servletContextConfigurer.setInitParameterBoolean("foo", true);
		servletContextConfigurer.setInitParameterBoolean("bar", false);

		assertThat(servletContext.getInitParameter("foo")).isEqualTo("true");
		assertThat(servletContext.getInitParameter("bar")).isEqualTo("false");
	}

	@Test
	public void setInitParameterCollection() throws Exception {
		servletContextConfigurer.setInitParameterCollection("foo", Arrays.asList("foo", "bar"), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter("foo")).isEqualTo("foo,bar");
	}

	@Test
	public void setInitParameterCollectionNull() throws Exception {
		servletContextConfigurer.setInitParameterCollection("foo", null, ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter("foo")).isNull();
	}

	@Test
	public void setInitParameterCollectionEmpty() throws Exception {
		servletContextConfigurer.setInitParameterCollection("foo", Collections.<String>emptyList(), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter("foo")).isEmpty();
	}

	@Test
	public void setInitParameterCollectionSingle() throws Exception {
		servletContextConfigurer.setInitParameterCollection("foo", Collections.singletonList("bar"), ServletContextConfigurer.Separator.COMMA);

		assertThat(servletContext.getInitParameter("foo")).isEqualTo("bar");
	}

}
