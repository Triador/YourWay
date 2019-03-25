package com.triador.yourwayserver.service;

import com.triador.yourwayserver.dao.model.*;
import com.triador.yourwayserver.dao.model.BookResponse;
import com.triador.yourwayserver.dao.repo.UserBookRepository;
import com.triador.yourwayserver.dao.repo.UserRepository;
import com.triador.yourwayserver.dto.request.AddUserBookRequest;
import com.triador.yourwayserver.dto.response.ProfileResponse;
import com.triador.yourwayserver.enumeration.ErrorMessage;
import com.triador.yourwayserver.exception.CommonException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {

    private UserBookRepository userBookRepository;
    private UserRepository userRepository;

    public ProfileService(UserBookRepository userBookRepository,
                          UserRepository userRepository) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
    }

    public void saveBook(AddUserBookRequest addUserBookRequest) {
        UserBookKey userBookKey = new UserBookKey(addUserBookRequest.getUserId(), addUserBookRequest.getBookId());
        UserBook userBook = new UserBook();
        userBook.setId(userBookKey);
        userBook.setStatus(addUserBookRequest.getBookStatus());
        userBookRepository.save(userBook);
    }

    public ProfileResponse findById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new CommonException(ErrorMessage.ERROR_NOT_FOUND.getMessage()));
        ProfileResponse.ProfileBuilder profileBuilder = ProfileResponse.builder();
        profileBuilder
                .username(user.getName())
                .imageLink(user.getImageLink());

        List<UserBook> fullBookInfoList = userBookRepository.findByIdUserId(userId);

        List<BookResponse> progressBookResponses = new ArrayList<>();
        List<BookResponse> futureBookResponses = new ArrayList<>();
        List<BookResponse> finishedBookResponses = new ArrayList<>();

        for (UserBook fullBookInfo : fullBookInfoList) {
            BookResponse bookResponse = new BookResponse();
            BeanUtils.copyProperties(fullBookInfo.getBook(), bookResponse);
            bookResponse.setNotes(fullBookInfo.getNotes());
            bookResponse.setStatus(fullBookInfo.getStatus());

            switch (fullBookInfo.getStatus()) {
                case PROGRESS: {
                    progressBookResponses.add(bookResponse);
                    break;
                }
                case FUTURE: {
                    futureBookResponses.add(bookResponse);
                    break;
                }
                case FINISHED: {
                    finishedBookResponses.add(bookResponse);
                }
            }
        }

        profileBuilder.progressBookResponses(progressBookResponses);
        profileBuilder.futureBookResponses(futureBookResponses);
        profileBuilder.finishedBookResponses(finishedBookResponses);

        return profileBuilder.build();
    }

    public void deleteBook(Integer userId, Integer bookId) {
        userBookRepository.deleteById(new UserBookKey(userId, bookId));
    }
}
