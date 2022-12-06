package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.service;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotExists;

import java.util.List;
import java.util.Optional;

// The above code is an interface which is used to define the methods which are to be implemented in the TrackServiceImpl
// class.
public interface TrackService {
    /**
     * Save a track to the database.
     *
     * @param track The track object to be saved.
     * @return The track object that was saved.
     */
    Track saveTrack(Track track) throws TrackAlreadyExists;

    /**
     * Get all tracks.
     *
     * @return A list of all the tracks in the database.
     */
    List<Track> getAllTracks();

    /**
     * Given a track ID, return the track if it exists, otherwise return an empty Optional.
     *
     * @param trackId The id of the track you want to get.
     * @return Optional<Track>
     */
    Optional<Track> getTrackById(int trackId);

    /**
     * Delete a track from the database.
     *
     * @param trackId The id of the track to delete.
     * @return boolean
     */
    boolean deleteTrackById(int trackId) throws TrackNotExists;

    /**
     * Find all tracks with the given track name.
     *
     * @param trackName The name of the track to search for.
     * @return A list of tracks with the given track name.
     */
    List<Track> findTrackTrackName(String trackName);

    List<Track> getTrackBtTrackRatingGreaterThan(int trackRating);

}
