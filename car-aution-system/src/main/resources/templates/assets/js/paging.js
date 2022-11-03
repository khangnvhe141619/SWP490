const element = document.querySelector(".paging ul");
let totalPages = 8;
let pageCurrent = 1;

element.innerHTML = createPaging(totalPages, pageCurrent);
function createPaging(totalPages, pageCurrent){
  let li = '';
  let active;
  let beforePage = pageCurrent - 1;
  let afterPage = pageCurrent + 1;
  if(pageCurrent > 1){ 
    li += `<li class="btn prev" onclick="createPaging(totalPages, ${pageCurrent - 1})"><span><i class="bi bi-chevron-left"></i> Prev</span></li>`;
  }

  if (pageCurrent == totalPages) {
    beforePage = beforePage - 1;
  }
  if (pageCurrent == 1) {
    afterPage = afterPage + 1;
  }

  for (var plength = beforePage; plength <= afterPage; plength++) {
    if (plength > totalPages) { 
      continue;
    }
    if (plength == 0) { 
      plength = plength + 1;
    }
    if(pageCurrent == plength){ 
      active = "active";
    }else{ 
      active = "";
    }
    li += `<li class="numb ${active}" onclick="createPaging(totalPages, ${plength})"><span>${plength}</span></li>`;
  }

  if (pageCurrent < totalPages) { 
    li += `<li class="btn next" onclick="createPaging(totalPages, ${pageCurrent + 1})"><span>Next <i class="bi bi-chevron-right"></i></span></li>`;
  }
  element.innerHTML = li; 
  return li; 
}