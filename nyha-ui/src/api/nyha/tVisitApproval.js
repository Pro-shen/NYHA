import request from "@/utils/request";

export function list(query) {
  return request({
    url: "/nyha/tVisitApproval/list",
    method: "get",
    params: query,
  });
}

export function getUserId(userName) {
  return request({
    url: "/nyha/tVisitApproval/getUserId/" + userName,
    method: "get",
  });
}

export function selectTVisitListById(id) {
    return request({
      url: "/nyha/tVisitApproval/selectTVisitListById/" + id,
      method: "get",
    });
  }
  
  export function edit(data) {
    return request({
      url: "/nyha/tVisitApproval/edit",
      method: "post",
      data: data,
    });
  }
  
  export function remove(data) {
    return request({
      url: "/nyha/tVisitApproval/remove",
      method: "post",
      data: data,
    });
  }
  

