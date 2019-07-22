package org.donglight.campus.guide.web.controller;

import org.donglight.campus.guide.entity.Distance;
import org.donglight.campus.guide.entity.ScenicSpot;
import org.donglight.campus.guide.service.IScenicSpotService;
import org.donglight.campus.guide.util.Response;
import org.donglight.campus.guide.util.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


/**
 * ScenicSpotController 景点数据REST API接口
 *
 * @author donglight
 * @date 2019/6/23
 * @since 1.0.0
 */
@RestController
@RequestMapping("/scenicSpots")
public class ScenicSpotController {

    private final IScenicSpotService scenicSpotService;

    @Autowired
    public ScenicSpotController(IScenicSpotService scenicSpotService) {
        this.scenicSpotService = scenicSpotService;
    }

    @GetMapping("")
    public Response listScenicSpot(@RequestParam(required = false) Integer schoolCode,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) Integer page,
                                   @RequestParam(required = false) Integer pageSize) {
        List<ScenicSpot> scenicSpots = scenicSpotService.listScenicSpot(schoolCode, name);
        if (page == null && pageSize == null) {
            scenicSpots = scenicSpotService.listScenicSpot(schoolCode, name);
        } else {
            Page<ScenicSpot> scenicSpotPage = scenicSpotService.listScenicSpot(schoolCode, name, page, pageSize);
            if (scenicSpotPage != null) {
                scenicSpots = scenicSpotPage.getContent();
            }
        }
        if (scenicSpots != null) {
            return Response.ok(scenicSpots);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("{id}")
    public Response scenicSpot(@PathVariable Long id) {
        ScenicSpot scenicSpot = scenicSpotService.getScenicSpot(id);
        if (scenicSpot != null) {
            return Response.ok(scenicSpot);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("/shortest_route")
    public Response findShortestRoute(@RequestParam(name = "start", defaultValue = "") String start,
                                      @RequestParam(name = "end", defaultValue = "") String end) {
        List<ScenicSpot> shortestPath = scenicSpotService.findShortestRoute(start, end);
        if (shortestPath != null) {
            return Response.ok(shortestPath);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("/shortest_route/distance")
    public Response findShortestRouteDistance(@RequestParam(name = "start", defaultValue = "") String start,
                                              @RequestParam(name = "end", defaultValue = "") String end) {
        Long metre = scenicSpotService.findShortestRouteDistance(start, end);
        if (metre != null) {
            Distance distance = new Distance(metre, null, null);
            return Response.ok(distance);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @GetMapping("/routes")
    public Response findRoutes(String start, String end) {
        List<ScenicSpot> routes = scenicSpotService.findRoutes(start, end);
        if (routes != null) {
            return Response.ok(routes);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @PostMapping("")
    public Response addScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException {
        ScenicSpot newScenicSpot = scenicSpotService.addScenicSpot(scenicSpot, file);
        if (newScenicSpot != null) {
            return Response.ok(newScenicSpot);
        } else {
            return Response.exception(ResponseEnum.SERVER_INTERNAL_ERROR);
        }
    }

    @PutMapping("")
    public Response editScenicSpot(ScenicSpot scenicSpot, MultipartFile file) throws IOException {
        ScenicSpot updatedScenicSpot = scenicSpotService.updateScenicSpot(scenicSpot, file);
        if (updatedScenicSpot != null) {
            return Response.ok(updatedScenicSpot);
        } else {
            return Response.exception(ResponseEnum.NOELEMENT);
        }
    }

    @DeleteMapping("{scenicSpotId}")
    public Response delScenicSpot(@PathVariable Long scenicSpotId) {
        scenicSpotService.deleteScenicSpot(scenicSpotId);
        return Response.ok();
    }

    @PostMapping("/route")
    public Response addRoute(@RequestParam(required = false) String start,
                             @RequestParam(required = false) String end,
                             @RequestParam(required = false) Long distance) {
        if (scenicSpotService.addRoute(start, end, distance)) {
            return Response.ok();
        }
        return Response.exception(ResponseEnum.NOELEMENT);
    }
}
