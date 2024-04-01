import request from "@/utils/request";

export function list(query) {
  return request({
    url: "/nyha/tFollowApproval/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tFollowApproval/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
    return request({
      url: "/nyha/tFollowApproval/add",
      method: "post",
      data: data,
    });
  }

export function selectTFollowListById(id) {
  return request({
    url: "/nyha/tFollowApproval/selectTFollowListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tFollowApproval/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tFollowApproval/remove",
    method: "post",
    data: data,
  });
}