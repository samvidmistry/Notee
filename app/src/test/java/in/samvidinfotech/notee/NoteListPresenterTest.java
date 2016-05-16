package in.samvidinfotech.notee;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.samvidinfotech.notee.data.NotesRepository;
import in.samvidinfotech.notee.noteslist.NotesListContract;
import in.samvidinfotech.notee.noteslist.NotesListPresenter;

import static org.mockito.Mockito.verify;

/**
 * Created by samvidmistry on 15/5/16.
 */
public class NoteListPresenterTest {
    private NotesListPresenter mPresenter;

    @Mock
    private NotesListContract.View mView;

    @Captor
    private ArgumentCaptor<NotesRepository.LoadNotesCallback> mLoadNotesCallbackArgumentCaptor;

    @Mock
    private NotesRepository mNotesRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new NotesListPresenter(mNotesRepository, mView);
    }

    @Test
    public void testLoadNotesFromRepoIntoUi() throws Exception {
        mPresenter.loadNotes();
        verify(mView).setProgressState(true);
        verify(mNotesRepository).getNotes(mLoadNotesCallbackArgumentCaptor.capture());
        mLoadNotesCallbackArgumentCaptor.getValue().onNotesLoaded();
        verify(mView).setProgressState(false);
        verify(mView).refreshNotes();
    }
}
