package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.NotesBackingBean;
import com.paritosh.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesBackingBeanToNotesTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesBackingBeanToNotes notesBackingBeanToNotes;

    @Before
    public void setUp() throws Exception {
        notesBackingBeanToNotes = new NotesBackingBeanToNotes();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(notesBackingBeanToNotes.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(notesBackingBeanToNotes.convert(new NotesBackingBean()));
    }

    @Test
    public void convert() throws Exception {
        //given
        NotesBackingBean notesBackingBean = new NotesBackingBean();
        notesBackingBean.setId(ID_VALUE);
        notesBackingBean.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes notes = notesBackingBeanToNotes.convert(notesBackingBean);

        //then
        assertNotNull(notes);
        assertEquals(ID_VALUE, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}