package br.com.fiap.lanchonete.cliente

import io.cucumber.core.options.Constants
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.Suite


@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, value = "src/test/resources/features/cliente.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME,value = "br.com.fiap.lanchonete.cliente.integration")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME,value = "pretty, html:target/cucumber-report/cucumber.html")
class RunCucumberTest  {
}