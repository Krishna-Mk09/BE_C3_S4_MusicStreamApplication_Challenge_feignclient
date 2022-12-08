/*
 * Author Name : M.Krishna.
 * Date: 24-11-2022
 * Created With: IntelliJ IDEA Community Edition
 *
 */


package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.service;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.proxy.UserProxy;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    // A field injection.
    private final TrackRepository trackRepository;
    private final UserProxy userProxy;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository, UserProxy userProxy) {
        this.trackRepository = trackRepository;
        this.userProxy = userProxy;
    }


    /**
     * It saves the track to the database.
     *
     * @param track The track object that needs to be saved.
     * @return Track
     */
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExists {
        if (trackRepository.findById(track.getTrackId()).isPresent()) {
            throw new TrackAlreadyExists();
        }
        userProxy.saveUser(track);
        return trackRepository.save(track);
    }

    /**
     * > The function returns a list of all tracks in the database
     *
     * @return A list of all tracks in the database.
     */
    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    /**
     * > This function returns the track with the given id
     *
     * @param trackId The id of the track to be searched.
     * @return Optional<Track>
     */
    @Override
    public Optional<Track> getTrackById(int trackId) {
        try {
            return trackRepository.findById(trackId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    /**
     * It deletes the track by id.
     *
     * @param trackId The id of the track to be deleted.
     * @return boolean
     */
    @Override
    public boolean deleteTrackById(int trackId) throws TrackNotExists {
        if (trackRepository.findById(trackId).isEmpty()) {
            throw new TrackNotExists();
        }
        trackRepository.deleteById(trackId);
        return true;
    }

    /**
     * > This function is used to find all the tracks with the given track name
     *
     * @param trackName The name of the track.
     * @return A list of tracks with the same track name.
     */
    @Override
    public List<Track> findTrackTrackName(String trackName) {
        return trackRepository.findAllTrackTrackName(trackName);
    }

    /**
     * > This function returns a list of tracks whose track rating is greater than the given track rating
     *
     * @param trackRating The track rating to be searched for.
     * @return List of tracks
     */
    @Override
    public List<Track> getTrackBtTrackRatingGreaterThan(int trackRating) {
        return trackRepository.findByTrackRatingGreaterThan(trackRating);
    }

}
