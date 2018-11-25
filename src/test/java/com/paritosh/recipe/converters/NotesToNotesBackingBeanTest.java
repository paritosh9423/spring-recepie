package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.NotesBackingBean;
import com.paritosh.recipe.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesBackingBeanTest {


    public static final Long ID_VALUE = new Long(1L);
    public static final String RECIPE_NOTES = "Notes";
    NotesToNotesBackingBean notesToNotesBackingBean;

    @Before
    public void setUp() throws Exception {
        notesToNotesBackingBean = new NotesToNotesBackingBean();
    }


    @Test
    public void convert() throws Exception {
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesBackingBean notesBackingBean = notesToNotesBackingBean.convert(notes);

        //then
        assertEquals(ID_VALUE, notesBackingBean.getId());
        assertEquals(RECIPE_NOTES, notesBackingBean.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(notesToNotesBackingBean.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(notesToNotesBackingBean.convert(new Notes()));
    }
}