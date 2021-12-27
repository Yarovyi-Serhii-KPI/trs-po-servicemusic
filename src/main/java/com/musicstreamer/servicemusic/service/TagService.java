package com.musicstreamer.servicemusic.service;

import com.musicstreamer.servicemusic.repo.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> fetchAllTags();
    Tag fetchTagById(long id) throws IllegalArgumentException;
    long createTag(String name);
    void updateTag(long id, String name) throws IllegalArgumentException;
    void deleteTag(long id);

}
