package br.com.rafael.githubuser.user.presentation.coordinator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.rafael.githubuser.user.domain.interactor.SaveStateToView;
import br.com.rafael.githubuser.user.presentation.UserContract;
import rx.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SaveStateCoordinatorTest {

    @Mock
    SaveStateToView saveStateToView;

    @Mock
    UserContract.State state;

    SaveStateCoordinator impl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        impl = spy(
                new SaveStateCoordinator(
                        saveStateToView)
        );
    }

    @Test
    public void callCoordinatorSaveState_shouldExecuteInOrder() {
        when(saveStateToView.call(any()))
                .thenReturn(Observable.just(state));

        InOrder callOrder = inOrder(
                saveStateToView);

        Observable.just(state)
                .compose(impl)
                .subscribe();

        callOrder.verify(saveStateToView).call(any());
    }
}
