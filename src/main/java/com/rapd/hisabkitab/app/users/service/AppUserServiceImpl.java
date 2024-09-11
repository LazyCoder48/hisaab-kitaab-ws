package com.rapd.hisabkitab.app.users.service;

import com.rapd.hisabkitab.app.pojo.AppRequestPojo;
import com.rapd.hisabkitab.app.pojo.AppResponsePojo;
import com.rapd.hisabkitab.app.users.entity.Users;
import com.rapd.hisabkitab.app.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final UsersRepository usersRepository;

    /**
     *
     * @param appRequestPojo
     * @return
     */
    @Override
    public ResponseEntity<AppResponsePojo> updateUser(AppRequestPojo appRequestPojo) {

        log.info("Update User {}", appRequestPojo);
        try {

            Users updateUsers = (Users) appRequestPojo.getData();


        } catch (Exception e) {
            log.error("error while updating user {} ", e.getMessage(), e);
        }


        return null;
    }

    /**
     *
     * @param appRequestPojo
     * @return
     */
    @Override
    public ResponseEntity<AppResponsePojo> logoutUser(AppRequestPojo appRequestPojo) {
        return null;
    }
}
