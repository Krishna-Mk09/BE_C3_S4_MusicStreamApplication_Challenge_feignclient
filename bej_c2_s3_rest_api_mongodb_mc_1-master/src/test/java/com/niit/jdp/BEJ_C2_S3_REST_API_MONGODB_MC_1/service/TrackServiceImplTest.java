package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.service;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrackServiceImplTest {
    @Mock
    private TrackRepository trackRepository;
    @InjectMocks
    private TrackServiceImpl trackServiceImpl;
    private Track track;
    private Artist trackArtist;


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
     * The function is used to save a track to the database
     */
    @Test
    void saveTrack() throws TrackAlreadyExists {
        // when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(any())).thenReturn(track);
        assertEquals(track, trackServiceImpl.saveTrack(track));
        verify(trackRepository, times(1)).save(any());
        verify(trackRepository, times(1)).findById(any());
    }

    /**
     * It checks if the track already exists in the database.
     */
    @Test
    void saveTracksException() throws TrackAlreadyExists {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertThrows(TrackAlreadyExists.class, () -> trackServiceImpl.saveTrack(track));
        verify(trackRepository, times(0)).save(any());

    }

    /**
     * It deletes the track by id.
     */
    @Test
    void deleteTrackById() throws TrackNotExists {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertTrue(trackServiceImpl.deleteTrackById(222));
    }


    /**
     * A test case for the deleteTrackById() function.
     */
    @Test
    void deleteTrackNot() throws TrackNotExists {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        assertThrows(TrackNotExists.class, () -> trackServiceImpl.deleteTrackById(222));
    }


}