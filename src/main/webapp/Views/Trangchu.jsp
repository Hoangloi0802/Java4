
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<title>Video management</title>
<!-- Required meta tags -->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />

<!-- Bootstrap CSS v5.2.1 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
</head>
<!-- Thêm jQuery từ CDN -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="shortcut icon" href="/ASS_PD10302/uploads/Logo2.png" />
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="/ASS_PD10302/Home">HoangLoi</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<c:choose>
							<c:when test="${not empty userName}">
								<!-- User logged in -->
								<li class="nav-item"><a class="nav-link active"
									aria-current="page" href="/ASS_PD10302/myFavorite">My
										Favorite</a></li>
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">
										${userName} </a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#"
											data-bs-toggle="modal" data-bs-target="#doimatkhau">Change
												Password</a></li>
										<li><a class="dropdown-item" href="#"
											data-bs-toggle="modal" data-bs-target="#chinhsua">Edit
												Profile</a></li>
										<li><a class="dropdown-item" href="/ASS_PD10302/logout" id = "dangxuat">Logoff</a></li>
										<c:if test="${sessionScope.userRole == true}">
											<li class="nav-item"><a class="dropdown-item"
												href="/ASS_PD10302/user/index">Admin Dashboard</a></li>
									</ul></li>
								</c:if>

							</c:when>
							<c:otherwise>
								<!-- User not logged in -->

								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle" href="#" role="button"
									data-bs-toggle="dropdown" aria-expanded="false">My Account</a>
									<ul class="dropdown-menu">
										<li><a class="dropdown-item" href="#"
											data-bs-toggle="modal" data-bs-target="#dangnhap">Login</a></li>
										<li><a class="dropdown-item" href="#"
											data-bs-toggle="modal" data-bs-target="#quenmatkhau">Forgot
												Password</a></li>
										<li><a class="dropdown-item" href="#"
											data-bs-toggle="modal" data-bs-target="#dangky">Registration</a></li>
									</ul></li>
							</c:otherwise>
						</c:choose>


					</ul>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container mt-4">
			<div class="row">
				<c:forEach var="video" items="${videos}">
					<div class="col-md-4 mb-4">
						<div class="card h-100">
							<a href="videoDetails?videoId=${video.id}"
								class="text-decoration-none"> <img src="${video.poster}"
								alt="Poster" class="card-img-top" />
							</a>
							<div class="card-body d-flex flex-column">

								<a href="videoDetails?videoId=${video.id}"
									class="text-decoration-none text-dark video-link">
									<h5 class="card-title">${video.title}</h5>
									<p class="card-text">${video.description}</p>
								</a>
							</div>

							<div class="card-footer">
								<div class="d-flex justify-content-between">
									<a href="javascript:void(0);"
										class="btn btn-primary like-button" data-id="${video.id}"
										onclick="likeVideo('${video.id}')"> <svg
											xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-hand-thumbs-up"
											viewBox="0 0 16 16">
                                <path
												d="M8.864.046C7.908-.193 7.02.53 6.956 1.466c-.072 1.051-.23 2.016-.428 2.59-.125.36-.479 1.013-1.04 1.639-.557.623-1.282 1.178-2.131 1.41C2.685 7.288 2 7.87 2 8.72v4.001c0 .845.682 1.464 1.448 1.545 1.07.114 1.564.415 2.068.723l.048.03c.272.165.578.348.97.484.397.136.861.217 1.466.217h3.5c.937 0 1.599-.477 1.934-1.064a1.86 1.86 0 0 0 .254-.912c0-.152-.023-.312-.077-.464.201-.263.38-.578.488-.901.11-.33.172-.762.004-1.149.069-.13.12-.269.159-.403.077-.27.113-.568.113-.857 0-.288-.036-.585-.113-.856a2 2 0 0 0-.138-.362 1.9 1.9 0 0 0 .234-1.734c-.206-.592-.682-1.1-1.2-1.272-.847-.282-1.803-.276-2.516-.211a10 10 0 0 0-.443.05 9.4 9.4 0 0 0-.062-4.509A1.38 1.38 0 0 0 9.125.111zM11.5 14.721H8c-.51 0-.863-.069-1.14-.164-.281-.097-.506-.228-.776-.393l-.04-.024c-.555-.339-1.198-.731-2.49-.868-.333-.036-.554-.29-.554-.55V8.72c0-.254.226-.543.62-.65 1.095-.3 1.977-.996 2.614-1.708.635-.71 1.064-1.475 1.238-1.978.243-.7.407-1.768.482-2.85.025-.362.36-.594.667-.518l.262.066c.16.04.258.143.288.255a8.34 8.34 0 0 1-.145 4.725.5.5 0 0 0 .595.644l.003-.001.014-.003.058-.014a9 9 0 0 1 1.036-.157c.663-.06 1.457-.054 2.11.164.175.058.45.3.57.65.107.308.087.67-.266 1.022l-.353.353.353.354c.043.043.105.141.154.315.048.167.075.37.075.581 0 .212-.027.414-.075.582-.05.174-.111.272-.154.315l-.353.353.353.354c.047.047.109.177.005.488a2.2 2.2 0 0 1-.505.805l-.353.353.353.354c.006.005.041.05.041.17a.9.9 0 0 1-.121.416c-.165.288-.503.56-1.066.56z" />
                            </svg> Like
									</a> <a href="#" class="btn btn-primary"> <svg
											xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-share" viewBox="0 0 16 16">
                                <path
												d="M13.5 1a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3M11 2.5a2.5 2.5 0 1 1 .603 1.628l-6.718 3.12a2.5 2.5 0 0 1 0 1.504l6.718 3.12a2.5 2.5 0 1 1-.488.876l-6.718-3.12a2.5 2.5 0 1 1 0-3.256l6.718-3.12A2.5 2.5 0 0 1 11 2.5m-8.5 4a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3m11 5.5a1.5 1.5 0 1 0 0 3 1.5 1.5 0 0 0 0-3" />
                            </svg> Share
									</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<script>
			
			document.addEventListener("DOMContentLoaded", () => {
			    fetch('likedVideos')
		        .then(response => response.json())
		        .then(data => {
		            if (data.success) {
		                const likedVideoIds = data.likedVideoIds || [];
		                document.querySelectorAll(".like-button").forEach(button => {
		                    const videoId = button.getAttribute("data-id");
		                    if (likedVideoIds.includes(videoId)) {
		                        updateButtonToLiked(button); // Cập nhật giao diện nút
		                    }
		                });
		            }
		        })
		        .catch(error => console.error("Error fetching liked videos:", error));
		});

		// Hàm cập nhật giao diện nút thành "Đã Like"
		function updateButtonToLiked(button) {
		    button.classList.remove("btn-primary"); 
		    button.classList.add("btn-secondary");
		    button.textContent = "Đã Like"; 
		    button.disabled = true;
		    button.style.pointerEvents = "none"; 
		}


		// Xử lý khi người dùng bấm nút Like
		function likeVideo(videoId) {
		    fetch('likeVideo', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/json',
		        },
		        body: JSON.stringify({ videoId: videoId }),
		    })
		    .then(response => response.json())
		    .then(data => {
		        if (data.success) {
		            const button = document.querySelector(`.like-button[data-id="${videoId}"]`);
		            if (button) {
		                updateButtonToLiked(button); // Cập nhật giao diện nút
		            }
		            alert('Video liked successfully!');
		            location.reload()
		        } else {
		            alert(data.message);
		            if (data.redirectUrl) {
		                window.location.href = data.redirectUrl; // Chuyển hướng nếu cần
		            }
		        }
		    })
		    .catch(error => {
		        console.error('Error liking video:', error);
		        alert('An error occurred. Please try again.');
		    });
		}
			
</script>
		</div>
	</main>
	<footer>
		<!-- place footer here -->
	</footer>
	<!-- Modal Dang nhap -->
	<div class="modal fade" id="dangnhap" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center border-bottom pb-3">Đăng nhập</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card p-4 rounded shadow-sm">
						<form action="Login" method="post">
							<div class="mb-3">
								<label for="idOrEmail" class="form-label">Email address</label>
								<input type="text" class="form-control" id="idOrEmail"
									name="idOrEmail" aria-describedby="emailHelp">
							</div>
							<div class="mb-3">
								<label for="password" class="form-label">Password</label> <input
									type="password" class="form-control" id="password"
									name="password">
							</div>
							<div class="mb-3 form-check">
								<input type="checkbox" class="form-check-input"
									id="exampleCheck1"> <label class="form-check-label"
									for="exampleCheck1">Remember me?</label>
							</div>
							<button type="submit" class="btn btn-primary">Login</button>
						</form>
						<!-- Error message container (shown conditionally) -->
						<%
						if (request.getAttribute("loginErrorMessage") != null) {
						%>
						<div class="alert alert-danger mt-3" role="alert">
							<%=request.getAttribute("loginErrorMessage")%>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("loginErrorMessage") != null) {
	%>
	<script>
		// This script will trigger the login modal to open if there's an error
		document.addEventListener("DOMContentLoaded", function() {
			var loginModal = new bootstrap.Modal(document
					.getElementById('dangnhap'));
			loginModal.show();
		});
	</script>
	<%
	}
	%>

	<!-- Modal dangky -->
	<div class="modal fade" id="dangky" tabindex="-2"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center border-bottom pb-3">Đăng ký</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card p-4 rounded shadow-sm">
						<form action="Dangky" method="post">
							<div class="row">
								<div class="col-md-6 col-12 mb-3">
									<label for="username" class="form-label">User name</label> <input
										type="text" class="form-control" id="username1" name="username1"
										aria-describedby="usernameHelp">
								</div>
								<div class="col-md-6 col-12 mb-3">
									<label for="password" class="form-label">Password</label> <input
										type="password" class="form-control" id="password1"
										name="password1">
								</div>
								<div class="col-md-6 col-12 mb-3">
									<label for="fullname" class="form-label">Full name</label> <input
										type="text" class="form-control" id="fullname1" name="fullname1">
								</div>
								<div class="col-md-6 col-12 mb-3">
									<label for="email" class="form-label">Email Address</label> <input
										type="email" class="form-control" id="email1" name="email1">
								</div>
							</div>
							<button type="submit" id="submit" class="btn btn-primary">Đăng ký</button>
						</form>

						<%
						if (request.getAttribute("registrationErrorMessage") != null) {
						%>
						<div class="alert alert-danger mt-3" role="alert">
							<%=request.getAttribute("registrationErrorMessage")%>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("registrationErrorMessage") != null) {
	%>
	<script>
		// This script will trigger the login modal to open if there's an error
		document.addEventListener("DOMContentLoaded", function() {
			var loginModal = new bootstrap.Modal(document
					.getElementById('dangky'));
			loginModal.show();
		});
	</script>
	<%
	}
	%>
	<!-- modal quen mat khau-->
	<div class="modal fade" id="quenmatkhau" tabindex="-2"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center border-bottom pb-3">Quên mật khẩu</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card p-4 rounded shadow-sm">
						<form action="SendPassword" method="post">
							<div class="row">
								<div class="mb-3">
									<label for="username" class="form-label">User name</label> <input
										type="text" class="form-control" id="username" name="username"
										placeholder="Nhập username">
								</div>
								<div class="mb-3">
									<label for="email" class="form-label">Email</label> <input
										type="email" class="form-control" id="email" name="email"
										placeholder="Nhập email">
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Gửi mật
								khẩu</button>
						</form>

						<!-- Hiển thị thông báo lỗi nếu có -->
						<%
						if (request.getAttribute("QuenerrorMessage") != null) {
						%>
						<div class="alert alert-danger mt-3" role="alert">
							<%=request.getAttribute("QuenerrorMessage")%>
						</div>
						<%
						}
						%>

						<!-- Hiển thị thông báo thành công nếu có -->
						<%
						if (request.getAttribute("QuensuccessMessage") != null) {
						%>
						<div class="alert alert-success mt-3" role="alert">
							<%=request.getAttribute("QuensuccessMessage")%>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Script tự động mở modal khi có thông báo -->
	<%
	if (request.getAttribute("QuenerrorMessage") != null || request.getAttribute("QuensuccessMessage") != null) {
	%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var modal = new bootstrap.Modal(document
					.getElementById('quenmatkhau'));
			modal.show();
		});
	</script>
	<%
	}
	%>

	<!--modal doimatkhau-->
	<div class="modal fade" id="doimatkhau" tabindex="-2"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center border-bottom pb-3">Đổi mật khẩu</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card p-4 rounded shadow-sm">
						<form action="ChangePassword" method="post">
							<div class="row">
								<div class="col-6 mb-3">
									<label for="username" class="form-label">User name</label> <input
										type="text" class="form-control" id="username" name="username"
										placeholder="Nhập username">
								</div>
								<div class="col-6 mb-3">
									<label for="currentPassword" class="form-label">Current
										Password</label> <input type="password" class="form-control"
										id="currentPassword" name="currentPassword"
										placeholder="Nhập mật khẩu hiện tại">
								</div>
								<div class="col-6 mb-3">
									<label for="newPassword" class="form-label">New
										Password</label> <input type="password" class="form-control"
										id="newPassword" name="newPassword"
										placeholder="Nhập mật khẩu mới">
								</div>
								<div class="col-6 mb-3">
									<label for="confirmNewPassword" class="form-label">Confirm
										New Password</label> <input type="password" class="form-control"
										id="confirmNewPassword" name="confirmNewPassword"
										placeholder="Xác nhận mật khẩu mới">
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Đổi mật
								khẩu</button>
						</form>
						<%
						if (request.getAttribute("Loi") != null) {
						%>
						<div class="alert alert-danger mt-3" role="alert">
							<%=request.getAttribute("Loi")%>
						</div>
						<%
						}
						%>
						<%
						if (request.getAttribute("Dung") != null) {
						%>
						<div class="alert alert-success mt-3" role="alert">
							<%=request.getAttribute("Dung")%>
						</div>
						<%
						}
						%>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	<%
	if (request.getAttribute("Loi") != null || request.getAttribute("Dung") != null) {
	%>
	<script>
		document.addEventListener("DOMContentLoaded", function() {
			var modal = new bootstrap.Modal(document
					.getElementById('doimatkhau'));
			modal.show();
		});
	</script>
	<%
	}
	%>

	<!--modal chinh sua-->
	<div class="modal fade" id="chinhsua" tabindex="-2"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="text-center border-bottom pb-3">Cập nhật tài khoản</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card p-4 rounded shadow-sm">
						<!-- Assuming 'user' data is passed from the controller -->
						<form action="UpdateProfile" method="post">
							<div class="row">
								<div class="col-6 mb-3">
									<label for="username" class="form-label">User name</label> <input
										type="text" class="form-control" id="username" name="username"
										value="${user.id != null ? user.id : ''}" readonly>
								</div>
								<div class="col-6 mb-3">
									<label for="password" class="form-label">Password</label> <input
										type="password" class="form-control" id="password"
										name="password" placeholder="Mật khẩu"
										value="${user.password != null ? user.password : ''}">
								</div>
								<div class="col-6 mb-3">
									<label for="fullname" class="form-label">Full name</label> <input
										type="text" class="form-control" id="fullname" name="fullname"
										value="${user.fullname != null ? user.fullname : ''}">
								</div>
								<div class="col-6 mb-3">
									<label for="email" class="form-label">Email Address</label> <input
										type="email" class="form-control" id="email" name="email"
										value="${user.email != null ? user.email : ''}">
								</div>
							</div>
							<button type="submit" class="btn btn-primary">Cập nhật</button>
						</form>

						<c:if test="${not empty errorMessage}">
							<div class="alert alert-danger mt-3" role="alert">
								${errorMessage}</div>
						</c:if>

						<c:if test="${not empty successMessage}">
							<div class="alert alert-success mt-3" role="alert">
								${successMessage}</div>
						</c:if>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>

	<c:if test="${not empty errorMessage or not empty successMessage}">
		<script>
        document.addEventListener("DOMContentLoaded", function() {
            var modalElement = document.getElementById('chinhsua');
            if (modalElement) {
                var modal = new bootstrap.Modal(modalElement);
                modal.show();
            }
        });
    </script>
	</c:if>


	<!-- Bootstrap JavaScript Libraries -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
		integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
		integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
		crossorigin="anonymous"></script>
</body>

</html>