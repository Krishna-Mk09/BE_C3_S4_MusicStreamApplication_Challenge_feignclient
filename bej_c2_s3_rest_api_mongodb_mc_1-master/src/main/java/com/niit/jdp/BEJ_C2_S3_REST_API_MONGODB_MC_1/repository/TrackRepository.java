package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.repository;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// This is a custom query to find all tracks by artist name.
@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {
    // This is a custom query to find all tracks by artist name.
    @Query("{'trackArtist.artistName':{$in:[?0]}}")
    List<Track> findAllTrackTrackName(String trackName);

    List<Track> findByTrackRatingGreaterThan(int rating);
}
