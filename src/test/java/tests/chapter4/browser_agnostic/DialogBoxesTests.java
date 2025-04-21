package tests.chapter4.browser_agnostic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DialogBoxesPage;
import tests.BaseTest;

import java.util.List;

public class DialogBoxesTests extends BaseTest {
    private DialogBoxesPage dialogBoxesPage;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        dialogBoxesPage = homePage.clickDialogBoxesLink();
    }

    @Test
    void defaultViewTest() {
        Assertions.assertEquals(List.of("Launch alert", "Launch confirm", "Launch prompt", "Launch modal"),
                dialogBoxesPage.getAllDialogBoxesButton(), "Incorrect launch buttons display");
    }

    @Test
    void launchAlertTest() {
        dialogBoxesPage
                .clickLaunchAlert()
                .checkAlertText("Hello world!");
    }

    @Test
    void launchConfirmTest() {
        dialogBoxesPage
                .clickLaunchConfirmAlert()
                .checkAlertText("Is this correct?")
                .acceptAlert()
                .checkAlertConfirmMessage("You chose: true")
                .clickLaunchConfirmAlert()
                .dismissAlert()
                .checkAlertConfirmMessage("You chose: false");
    }

    @Test
    void launchPromptTest() {
        String nameToEnter = "name";

        dialogBoxesPage
                .clickLaunchPromptAlert()
                .checkAlertText("Please enter your name")
                .acceptAlert()
                .checkAlertPromptMessage("You typed:")
                .clickLaunchPromptAlert()
                .dismissAlert()
                .checkAlertPromptMessage("You typed: null")
                .clickLaunchPromptAlert()
                .sendKeysToAlert(nameToEnter)
                .acceptAlert()
                .checkAlertPromptMessage("You typed: " + nameToEnter);
    }

    @Test
    void launchModalTest() {
        dialogBoxesPage
                .clickModalAlert()
                .checkModalTitle("Modal title")
                .checkModalBody("This is the modal body")
                .saveModal()
                .checkModalMessage("You chose: Save changes")
                .clickModalAlert()
                .closeModal()
                .checkModalMessage("You chose: Close");
    }
}
