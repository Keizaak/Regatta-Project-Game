package fr.ensicaen.genielogiciel.mvp.presenter;

import fr.ensicaen.genielogiciel.mvp.view.LoginView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginPresenterTest {

    @Mock
    private LoginView _view;

    @Parameterized.Parameters
    public static Collection<String[]> usernames() {
        return Arrays.asList(new String[][]{
                {"", "Bonjour inconnu !"},
                {"Test", "Bonjour Test"}});
    }

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_activate_view_error_display() {
        // given
        LoginPresenter presenter = new LoginPresenter();
        presenter.setView(_view);

        // when
        presenter.launchGame("");

        // then
        verify(_view, times(1)).displayError("Erreur : Manque le pseudo");
    }
}
