package io.dxps.springboot.sb3refreshkb.ex1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CourseController {

    @RequestMapping("/courses")
    List<Course> getCourses() {
        return Arrays.asList(
                new Course("1", "First course", "1st course's description"),
                new Course("2", "Second course", "2nd course's description"),
                new Course("3", "Third course", "3rd course's description")
        );
    }

}
