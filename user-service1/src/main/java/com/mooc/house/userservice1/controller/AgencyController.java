package com.mooc.house.userservice1.controller;

import java.util.List;

import com.mooc.house.common.page.PageParams;
import com.mooc.house.userservice1.common.ListResponse;
import com.mooc.house.userservice1.common.RestResponse;
import com.mooc.house.userservice1.model.Agency;
import com.mooc.house.userservice1.model.User;
import com.mooc.house.userservice1.service.AgencyService;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("agency")
public class AgencyController {

  @Autowired
  private AgencyService agencyService;

  @RequestMapping("add")
  public RestResponse<Object> addAgency(@RequestBody Agency agency) {
    agencyService.add(agency);
    return RestResponse.success();
  }

  @RequestMapping("list")
  public RestResponse<List<Agency>> agencyList() {
    List<Agency> agencies = agencyService.getAllAgency();
    return RestResponse.success(agencies);
  }


  @RequestMapping("agentList")
  public RestResponse<ListResponse<User>> agentList(Integer limit, Integer offset) {
    PageParams pageParams = new PageParams();
    pageParams.setLimit(limit);
    pageParams.setOffset(offset);
    Pair<List<User>, Long> pair = agencyService.getAllAgent(pageParams);
    ListResponse<User> response = ListResponse.build(pair.getKey(), pair.getValue());
    return RestResponse.success(response);
  }

  @RequestMapping("agentDetail")
  public RestResponse<User> agentDetail(Long id) {
    User user = agencyService.getAgentDetail(id);
    return RestResponse.success(user);
  }

  @RequestMapping("agencyDetail")
  public RestResponse<Agency> agencyDetail(Integer id) {
    Agency agency = agencyService.getAgency(id);
    return RestResponse.success(agency);
  }

}

