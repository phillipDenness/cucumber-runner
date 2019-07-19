package com.phillip.denness.cucumber.runner.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phillip.denness.cucumber.runner.DailyFundResponse;
import com.phillip.denness.cucumber.runner.utils.TestUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DailyFundStepDefinitions {

    @Value("${endpoint.daily-fund.host}")
    private String dailyFundHost;

    @Value("${endpoint.daily-fund.port}")
    private String dailyFundPort;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private TestUtils testUtils;

    private String trustnetUrl;
    private String citicode;
    private HttpResponse response;
    private DailyFundResponse dailyFundResponse;
    private CloseableHttpClient httpClient;

    @Given("I have a trustnet URL (.*)")
    public void iHaveATrustnetURLHttpsWwwTrustnetComFactsheetsPIbbAvInvestecDiversifiedIncomeFpPn(String trustnetUrl) {
        this.trustnetUrl = trustnetUrl;
    }

    @And("I have a trustnet citicode (.*)")
    public void iHaveATrustnetCiticodeO(String citicode) {
        this.citicode = citicode;
    }

    @When("I send a request to the daily scraper with citicode as a query param")
    public void iSendARequestToTheDailyScraperWithCiticodeAsAQueryParam() throws URISyntaxException, IOException {
        httpClient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(getDailyFundEndpoint())
                .setParameter("citicode", citicode);

        sendRequestAndConvertDailyFundResponse(builder);
    }

    @When("I send a request to the daily scraper with trustnet URL and citicode as a query params")
    public void iSendARequestToTheDailyScraperWithTrustnetURLAndCiticodeAsAQueryParams() throws URISyntaxException, IOException {
        httpClient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(getDailyFundEndpoint())
                .setParameter("url", trustnetUrl)
                .setParameter("citicode", citicode);

        sendRequestAndConvertDailyFundResponse(builder);
    }

    @When("I send a request to the daily scraper with trustnet URL as a query param")
    public void iSendARequestToTheDailyScraperWithTrustnetURLAsAQueryParam() throws IOException, URISyntaxException {
        httpClient = HttpClients.createDefault();

        URIBuilder builder = new URIBuilder(getDailyFundEndpoint())
                .setParameter("url", trustnetUrl);

        sendRequestAndConvertDailyFundResponse(builder);
    }

    @Then("I get a {int} response code")
    public void i_get_a_response_code(Integer responseCode) {
        // Write code here that turns the phrase above into concrete actions
        assertThat(response.getStatusLine().getStatusCode(), is(responseCode));
    }

    @And("I have a valid price")
    public void iHaveAValidPrice() {
        assertThat(Double.valueOf(dailyFundResponse.getPrice()), is(notNullValue()));
    }

    @And("I have a valid date")
    public void iHaveAValidDate() {
        assertThat(testUtils.isThisDateValid(dailyFundResponse.getDate(), "dd/MM/yyyy"), is(true));
    }

    @And("I have a valid fund name (.*)")
    public void iHaveAValidFundNameAvBaillieGiffordManagedFPPn(String fund) {
        assertThat(dailyFundResponse.getFund(), is(fund));
    }

    @And("I have a valid difference in price")
    public void iHaveAValidDifferenceInPrice() {
        assertThat(dailyFundResponse.getDifference(), is(notNullValue()));
    }

    private void sendRequestAndConvertDailyFundResponse(URIBuilder builder) throws URISyntaxException, IOException {
        HttpGet request = new HttpGet(builder.build());
        response = httpClient.execute(request);

        try {
            dailyFundResponse = mapper.readValue(response.getEntity().getContent(), DailyFundResponse.class);
        } catch (IOException ex) {
            System.out.println("Exception occured transforming response to DailyFundResponse but this might be expected...");
        }
    }

    private String getDailyFundEndpoint() {
        return dailyFundHost + ":" + dailyFundPort + "/api/today";
    }

 
}