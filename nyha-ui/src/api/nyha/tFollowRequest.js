import request from "@/utils/request";

export function list(query) {
  return request({
    url: "/nyha/tFollowRequest/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tFollowRequest/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
    return request({
      url: "/nyha/tFollowRequest/add",
      method: "post",
      data: data,
    });
  }

export function selectTFollowListById(id) {
  return request({
    url: "/nyha/tFollowRequest/selectTFollowListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tFollowRequest/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tFollowRequest/remove",
    method: "post",
    data: data,
  });
}