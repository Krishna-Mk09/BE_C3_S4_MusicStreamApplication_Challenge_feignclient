package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.repository;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class TrackRepositoryTest {
    private TrackRepository trackRepository;
    private Track track;
    private Artist trackArtist, trackArtist2;

    @BeforeEach
    void setUp() {
        trackArtist = new Artist(111, "vamshi");
        track = new Track(222, "isa", 7, trackArtist);
    }

    @AfterEach
    void tearDown() {
        track = null;
        trackArtist = null;
    }

    /**
     * It checks if the track name is equal to isa.
     */
    @Test
    void findByTrackName() {
        Assertions.assertEquals("isa", track.getTrackName());
    }

    @Test
    void findByTrackNameNotEquals() {
        Assertions.assertNotEquals("dfg", track.getTrackName());
    }

    @Test
    void findByTrackId() {
        Assertions.assertEquals(222, track.getTrackId());
    }

    @Test
    void findByTrackIdNotEquals() {
        Assertions.assertNotEquals(22, track.getTrackId());
    }

    @Test
    void fndByRating() {
        Assertions.assertEquals(7, track.getTrackRating());
    }

    @Test
    void fndByRatingNotEquals() {
        Assertions.assertNotEquals(7.5, track.getTrackRating());
    }

    @Test
    void findByArtistName() {
        Assertions.assertEquals("vamshi", trackArtist.getArtistName());
    }

    @Test
    void findByArtistNameNotEquals() {
        Assertions.assertNotEquals("ji", trackArtist.getArtistName());
    }

    @Test
    void findByArtistId() {
        Assertions.assertEquals(111, trackArtist.getArtistId());
    }

    @Test
    void findByArtistIdNot() {
        Assertions.assertNotEquals(11, trackArtist.getArtistId());
    }

}