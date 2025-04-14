import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.DialogBoxesPage;

import java.util.List;

public class DialogBoxesTest extends BaseTest {
    private DialogBoxesPage dialogBoxesPage;

    @BeforeEach
    void navigateToDialogBoxesPage() {
        dialogBoxesPage = homePage.clickDialogBoxesLink();
    }

    @Test
    void defaultViewTest() {
        Assertions.assertEquals(List.of("Launch alert", "Launch confirm", "Launch prompt", "Launch modal"),
                dialogBoxesPage.getAllDialogBoxesButton(), "Incorrect launch buttons display");
    }

    @Test
    void launchAlertTest() {
        dialogBoxesPage.clickLaunchAlert();
        Assertions.assertEquals("Hello world!", dialogBoxesPage.getAlertText(), "Incorrect alert text");
    }

    @Test
    void launchConfirmAcceptTest() {
        dialogBoxesPage.clickLaunchConfirmAlert();
        Assertions.assertEquals("Is this correct?", dialogBoxesPage.getAlertText(), "Incorrect alert text");

        dialogBoxesPage.acceptAlert();
        Assertions.assertEquals("You chose: true", dialogBoxesPage.getAlertConfirmMessage(), "Alert was not accepted");
    }

    @Test
    void launchConfirmDismissTest() {
        dialogBoxesPage.clickLaunchConfirmAlert();
        dialogBoxesPage.dismissAlert();
        Assertions.assertEquals("You chose: false", dialogBoxesPage.getAlertConfirmMessage(), "Alert was not dismissed");
    }

    @Test
    void launchPromptAcceptTest() {
        dialogBoxesPage.clickLaunchPromptAlert();
        Assertions.assertEquals("Please enter your name", dialogBoxesPage.getAlertText(), "Incorrect alert text");

        dialogBoxesPage.acceptAlert();
        Assertions.assertEquals("You typed:", dialogBoxesPage.getAlertPromptMessage(), "Alert was not accepted");
    }

    @Test
    void launchPromptTextTest() {
        String nameToEnter = "NAME";

        dialogBoxesPage.clickLaunchPromptAlert();
        dialogBoxesPage.sendKeysToAlert(nameToEnter);
        dialogBoxesPage.acceptAlert();

        Assertions.assertEquals("You typed: " + nameToEnter, dialogBoxesPage.getAlertPromptMessage(), "Text from alert is wrong");
    }

    @Test
    void launchPromptDismissTest() {
        dialogBoxesPage.clickLaunchPromptAlert();
        dialogBoxesPage.dismissAlert();

        Assertions.assertEquals("You typed: null", dialogBoxesPage.getAlertPromptMessage(), "Alert was not dismissed");
    }

    @Test
    void launchModalViewTest() {
        dialogBoxesPage.clickModalAlert();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Modal title", dialogBoxesPage.getModalTitle(),
                        "Incorrect modal title"),
                () -> Assertions.assertEquals("This is the modal body", dialogBoxesPage.getModalBody(),
                        "Incorrect modal body")
        );
    }

    @Test
    void launchModalCloseTest() {
        dialogBoxesPage.clickModalAlert();
        dialogBoxesPage.closeModal();
        Assertions.assertEquals("You chose: Close", dialogBoxesPage.getModalMessage(), "Modal selection was wrong");
    }

    @Test
    void launchModalSaveTest() {
        dialogBoxesPage.clickModalAlert();
        dialogBoxesPage.saveModal();
        Assertions.assertEquals("You chose: Save changes", dialogBoxesPage.getModalMessage(), "Modal selection is wrong");
    }
}
