/*
 * Author Name : M.Krishna.
 * Date: 25-11-2022
 * Created With: IntelliJ IDEA Community Edition
 *
 */


package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Track with specified code Not exists")
public class TrackNotExists extends Exception {
}
