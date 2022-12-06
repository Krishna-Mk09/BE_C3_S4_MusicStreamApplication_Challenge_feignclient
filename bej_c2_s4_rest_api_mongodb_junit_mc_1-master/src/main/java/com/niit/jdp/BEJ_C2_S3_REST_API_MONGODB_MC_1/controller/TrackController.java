/*
 * Author Name : M.Krishna.
 * Date: 24-11-2022
 * Created With: IntelliJ IDEA Community Edition
 *
 */


package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.controller;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackAlreadyExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception.TrackNotExists;
import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    // A private variable of type TrackService.
    private final TrackService trackService;

    // A constructor injection.
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * The function is a POST request that takes in a JSON object and returns a JSON object
     *
     * @param track The object that is to be saved in the database.
     * @return ResponseEntity<?>
     */
    @PostMapping("insertTrack")
    public ResponseEntity<?> saveFunction(@RequestBody Track track) throws TrackAlreadyExists {
        try {
            return new ResponseEntity<>(trackService.saveTrack(track), HttpStatus.CREATED);
        } catch (TrackAlreadyExists e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error!!!Try after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * It returns a list of all the tracks in the database
     *
     * @return A list of all the tracks in the database.
     */
    @GetMapping("/fetchAllTracks")
    public ResponseEntity<?> fetchAllTracksFunction() {
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.CREATED);
    }

    /**
     * This function is used to fetch all the tracks by their id
     *
     * @param trackId The id of the track you want to fetch.
     * @return ResponseEntity<?>
     */
    @GetMapping("/fetchAllTracksById/{trackId}")
    public ResponseEntity<?> fetchAllTracksByIdFunction(@PathVariable int trackId) {
        return new ResponseEntity<>(trackService.getTrackById(trackId), HttpStatus.CREATED);
    }

    /**
     * This function is used to fetch all the tracks by track name
     *
     * @param trackName The name of the track.
     * @return ResponseEntity<?>
     */

    @GetMapping("/fetchTrackByArtistName/{trackName}")
    public ResponseEntity<?> fetchAllTracksByNameFunction(@PathVariable String trackName) {
        return new ResponseEntity<>(trackService.findTrackTrackName(trackName), HttpStatus.CREATED);

    }

    /**
     * This function deletes a track by its id
     *
     * @param trackId The id of the track to be deleted.
     * @return ResponseEntity is being returned.
     */
    @DeleteMapping("/DeleteTracksById/{trackId}")
    public ResponseEntity<?> DeleteTracksByIdFunction(@PathVariable int trackId) throws TrackNotExists {
        try {
            return new ResponseEntity<>(trackService.deleteTrackById(trackId), HttpStatus.OK);
        } catch (TrackNotExists e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            return new ResponseEntity<>("Server Error!!!Try after Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function is used to fetch all the tracks by track rating greater than the given track rating
     *
     * @param trackRating The rating of the track.
     * @return ResponseEntity<?>
     */
    @GetMapping("/fetchTrackByRating/{trackRating}")
    public ResponseEntity<?> fetchAllTracksByRatingFunction(@PathVariable int trackRating) {
        return new ResponseEntity<>(trackService.getTrackBtTrackRatingGreaterThan(trackRating), HttpStatus.CREATED);

    }
}
