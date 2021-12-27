package com.musicstreamer.servicemusic.service.impl;

import com.musicstreamer.servicemusic.repo.TagRepo;
import com.musicstreamer.servicemusic.repo.model.Tag;
import com.musicstreamer.servicemusic.service.TagService;

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

    public long createTag(String name) {
        final Tag tag = new Tag(name);
        final Tag savedTag = tagRepo.save(tag);

        return savedTag.getId();
    }

    public void updateTag(long id, String name) throws IllegalArgumentException {
        final Optional<Tag> optTag = tagRepo.findById(id);

        if (optTag.isEmpty())
            throw new IllegalArgumentException("Invalid tag ID");

        final Tag tag = optTag.get();
        if (name != null && !name.isBlank()) tag.setName(name);
        tagRepo.save(tag);
    }

    public void deleteTag(long id) {
        tagRepo.deleteById(id);
    }
}
