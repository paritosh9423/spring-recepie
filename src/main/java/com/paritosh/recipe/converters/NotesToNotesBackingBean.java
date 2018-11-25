package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.NotesBackingBean;
import com.paritosh.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesBackingBean implements Converter<Notes, NotesBackingBean> {
    @Synchronized
    @Nullable
    @Override
    public NotesBackingBean convert(Notes notes) {
        if(notes==null)
            return null;
        NotesBackingBean notesBackingBean = new NotesBackingBean();
        notesBackingBean.setId(notes.getId());
        notesBackingBean.setRecipeNotes(notes.getRecipeNotes());
        return  notesBackingBean;

    }
}
