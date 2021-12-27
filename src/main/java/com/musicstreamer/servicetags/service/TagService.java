package com.musicstreamer.servicetags.service;

import com.musicstreamer.servicetags.repo.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> fetchAllTags();
    Tag fetchTagById(long id) throws IllegalArgumentException;
    long createTag(String name);
    void updateTag(long id, String name) throws IllegalArgumentException;
    void deleteTag(long id);

}
