package com.musicstreamer.servicetags.service.impl;

import com.musicstreamer.servicetags.repo.TagRepo;
import com.musicstreamer.servicetags.repo.model.Tag;
import com.musicstreamer.servicetags.service.TagService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;

    public List<Tag> fetchAllTags(){
        return tagRepo.findAll();
    }

    public Tag fetchTagById(long id) throws IllegalArgumentException {
        final Optional<Tag> optTag = tagRepo.findById(id);

        if (optTag.isPresent())
            return optTag.get();
        else
            throw new IllegalArgumentException("Invalid tag ID");
    }

    public long createTag(String name, int song) {
        final Tag tag = new Tag(name,song);
        final Tag savedTag = tagRepo.save(tag);

        return savedTag.getId();
    }

    public void updateTag(long id, String name, int song) throws IllegalArgumentException {
        final Optional<Tag> optTag = tagRepo.findById(id);

        if (optTag.isEmpty())
            throw new IllegalArgumentException("Invalid tag ID");

        final Tag tag = optTag.get();
        if (name != null && !name.isBlank()) tag.setName(name);
        if (song > 0) tag.setSong(song);
        tagRepo.save(tag);
    }

    public void deleteTag(long id) {
        tagRepo.deleteById(id);
    }
}
