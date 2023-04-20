document.addEventListener("DOMContentLoaded", () => {
  const reviewAddForm = document.getElementById("reviewAddForm");
  const reviewEditForm = document.getElementById("reviewEditForm");
  const jobBoardIdPk = document.getElementById("jobBoardIdPk").value;

  reviewAddForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const contentReview = document.getElementById("contentReview").value;
    const rstar = document.getElementById("rstar").value;

    const reviewData = {
      contentReview,
      rstar,
    };

    try {
      const response = await fetch(`/api/jobBoards/${jobBoardIdPk}/inquiry`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(reviewData),
      });

      if (response.ok) {
        alert("리뷰가 등록되었습니다.");
        location.reload();
      } else {
        alert("리뷰 등록에 실패하였습니다.");
      }
    } catch (error) {
      console.error(error);
      alert("오류가 발생하였습니다.");
    }
  });

  const editButtons = document.querySelectorAll("button[data-review-id]");
  editButtons.forEach((button) => {
    button.addEventListener("click", (e) => {
      const reviewId = e.target.getAttribute("data-review-id");
      reviewEditForm.style.display = "block";
      document.getElementById("reviewIdPK").value = reviewId;
    });
  });

  reviewEditForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const reviewIdPK = document.getElementById("reviewIdPK").value;
    const contentReview = document.getElementById("editContentReview").value;
    const rstar = document.getElementById("editRstar").value;

    const reviewData = {
      contentReview,
      rstar,
    };

    try {
      const response = await fetch(`/api/jobBoards/${jobBoardIdPk}/${reviewIdPK}`, {
        method: "PATCH",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(reviewData),
      });

      if (response.ok) {
        alert("리뷰가 수정되었습니다.");
        location.reload();
      } else {
        alert("리뷰 수정에 실패하였습니다.");
      }
    } catch (error) {
      console.error(error);
      alert("오류가 발생하였습니다.");
    }

  const deleteButtons = document.querySelectorAll("button[data-review-id]");
  deleteButtons.forEach((button) => {
    button.addEventListener("click", async (e) => {
      const reviewId = e.target.getAttribute("data-review-id");

      try {
        const response = await fetch(`/api/jobBoards/${jobBoardIdPk}/inquiry/${reviewId}`, {
          method: "DELETE",
        });

        if (response.ok) {
          alert("리뷰가 삭제되었습니다.");
          location.reload();
        } else {
          alert("리뷰 삭제에 실패하였습니다.");
        }
      } catch (error) {
        console.error(error);
        alert("오류가 발생하였습니다.");
      }
    });
  });
});