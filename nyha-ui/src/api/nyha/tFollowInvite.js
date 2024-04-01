import request from "@/utils/request";

export function list(query) {
  return request({
    url: "/nyha/tFollowInvite/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tFollowInvite/getUserId/" + userName,
    method: "get",
  });
}

export function add(data) {
    return request({
      url: "/nyha/tFollowInvite/add",
      method: "post",
      data: data,
    });
  }

export function selectTFollowListById(id) {
  return request({
    url: "/nyha/tFollowInvite/selectTFollowListById/" + id,
    method: "get",
  });
}

export function edit(data) {
  return request({
    url: "/nyha/tFollowInvite/edit",
    method: "post",
    data: data,
  });
}

export function remove(data) {
  return request({
    url: "/nyha/tFollowInvite/remove",
    method: "post",
    data: data,
  });
}