package com.paritosh.recipe.converters;

import com.paritosh.recipe.backingBeans.NotesBackingBean;
import com.paritosh.recipe.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesBackingBeanToNotes implements Converter<NotesBackingBean, Notes> {
    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesBackingBean notesBackingBean) {
        if(notesBackingBean == null)
            return null;
        Notes notes = new Notes();
        notes.setId(notesBackingBean.getId());
        notes.setRecipeNotes(notesBackingBean.getRecipeNotes());
        return notes;
    }
}
