package com.musicstreamer.servicetags.service.impl;

import com.musicstreamer.servicetags.api.dto.SongDto;
import com.musicstreamer.servicetags.repo.TagRepo;
import com.musicstreamer.servicetags.repo.model.Tag;
import com.musicstreamer.servicetags.service.TagService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class TagServiceImpl implements TagService {
    private final TagRepo tagRepo;
    private final String songUrl = "http://servicesong:8080/song/";

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

    public long createTag(String name, long song) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpEntity<Long> request = new HttpEntity<>(song);
        try {
            final ResponseEntity<SongDto> response = restTemplate.exchange(songUrl + song, HttpMethod.GET, request, SongDto.class);
        } catch(HttpClientErrorException e) { throw new IllegalArgumentException(String.format("Song with given id = %d was not found", song));}

        final Tag tag = new Tag(name,song);
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
