package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Artist;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.service.TrackService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TrackService trackService;
    @InjectMocks
    private TrackController trackController;
    private Track track;
    private Artist trackArtist;

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            e.printStackTrace();

        }
        return result;
    }


    @BeforeEach
    void setUp() {
        trackArtist = new Artist(222, "asdf");
        track = new Track(111, "isa", 9, trackArtist);
        mockMvc = MockMvcBuilders.standaloneSetup(trackController).build();
    }


    @AfterEach
    void tearDown() {
        track = null;
        trackArtist = null;
    }

    @Test
    public void saveTrackTestSuccess() throws Exception {
        when(trackService.saveTrack(any())).thenReturn(track);
        mockMvc.perform(post("/api/v1/insertTrack").contentType(MediaType.APPLICATION_JSON).content(jsonToString(track))).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void saveTrackTestFailure() throws Exception {
        when(trackService.saveTrack(any())).thenThrow(TrackAlreadyExists.class);
        mockMvc.perform(post("/api/v1/insertTrack").contentType(MediaType.APPLICATION_JSON).content(jsonToString(track))).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }


    @Test
    public void deleteTrackSuccess() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/DeleteTracksById/111").contentType(MediaType.APPLICATION_JSON).content(jsonToString(track))).andExpect(status().is2xxSuccessful()).andDo(MockMvcResultHandlers.print());
        verify(trackService, times(1)).deleteTrackById(anyInt());

    }

    @Test
    public void deleteTrackFailure() throws Exception {
        when(trackService.deleteTrackById(anyInt())).thenThrow(TrackNotExists.class);
        mockMvc.perform(delete("/api/v1/DeleteTracksById/111").contentType(MediaType.APPLICATION_JSON).content(jsonToString(track))).andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
    }


}