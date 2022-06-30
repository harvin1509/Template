package stepDefinations;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import stepLibrary.StepLibrary;

public class StepDefination extends StepLibrary {

    StepLibrary objStepLibrary = new StepLibrary();

    @Given("^Block \"([^\"]*)\" scenario and file type \"([^\"]*)\" is created with following details$")
    public void blockScenarioAndFileTypeIsCreatedWithFollowingDetails(String scenario, String fileType, DataTable table) throws Throwable {
        System.out.println("inside Step Defination");
        objStepLibrary.blockCreationForFile(scenario, fileType, table);
    }

//    @Then("^user loads the xml file 'Scenario-Name' for 'file-type' for date published on day 'D'$")
//    public void userLoadsTheXmlFileScenarioNameForFileTypeForDatePublishedOnDayD(String folder, String fileType, String datePublishedday) {
//        objStepLibrary.userLoadsTheXmlFileScenarioNameForFileTypeForDatePublishedOnDayD(folder, fileType, datePublishedday);
//    }

    @Then("^user loads the xml file \"([^\"]*)\" for \"([^\"]*)\" for date published on day \"([^\"]*)\"$")
    public void user_loads_the_xml_file_for_for_date_published_on_day(String folder, String fileType, String datePublishedday) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("insie file loading step");
        objStepLibrary.userLoadsTheTxtFileScenarioNameForFileTypeForDatePublishedOnDayD(folder , fileType , datePublishedday);
    }

    @Given("^Block \"([^\"]*)\" scenario and xml file type \"([^\"]*)\" is created with following details$")
    public void blockScenarioAndXmlFileTypeIsCreatedWithFollowingDetails(String folder, String fileType, String datePublishedday){
        // Write code here that turns the phrase above into concrete actions
        objStepLibrary.userLoadsXMLFile(folder , fileType , datePublishedday);
    }
}
