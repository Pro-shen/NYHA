import request from "@/utils/request";

export function list(query) {
  return request({
    url: "/nyha/tFollowDetails/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tFollowDetails/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
    return request({
      url: "/nyha/tFollowDetails/add",
      method: "post",
      data: data,
    });
  }

export function selectTFollowListById(id) {
  return request({
    url: "/nyha/tFollowDetails/selectTFollowListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tFollowDetails/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tFollowDetails/remove",
    method: "post",
    data: data,
  });
}