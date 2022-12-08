package com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.proxy;

import com.niit.jdp.BEJ_C2_S3_REST_API_MONGODB_MC_1.domain.Track;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name ="UserAuthenticate" ,url ="UserAuthenticate:8081" )
public interface UserProxy {
    @PostMapping("userservice/register/")
    public ResponseEntity<?> saveUser(@RequestBody Track track);

}
