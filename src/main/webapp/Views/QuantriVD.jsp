<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">

<head>
<title>Quản trị Videos</title>
<link rel="shortcut icon" href="/ASS_PD10302/uploads/Logo2.png" />
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

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container">
				<a class="navbar-brand" href="#">HoangLoi</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/ASS_PD10302/Home"> Home</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/ASS_PD10302/video/index"> Video</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="/ASS_PD10302/user/index"> User</a></li>
						<li class="nav-item"><a class="nav-link active"
							aria-current="page" href="#"> Reports</a></li>
				</div>
			</div>
		</nav>
	</header>
	<main>
		<div class="container mt-5">
			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="editor-tab"
						data-bs-toggle="tab" data-bs-target="#editor" type="button"
						role="tab" aria-controls="editor" aria-selected="true">Video
						Editor</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="list-tab" data-bs-toggle="tab"
						data-bs-target="#list" type="button" role="tab"
						aria-controls="list" aria-selected="false">Video List</button>
				</li>
			</ul>
			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="editor" role="tabpanel"
					aria-labelledby="editor-tab">
					<div class="row mt-3">
						<c:url var="url" value="/video" />
						<form method="post" enctype="multipart/form-data">
							<div class="row">
								<!-- Hình ảnh poster -->
								<div class="col-6">
									<img id="posterPreview"
										src="${video.poster != null ? video.poster : ''}"
										class="img-thumbnail mt-3" style="width: 100%;"
										alt="Poster Preview">
								</div>

								<!-- Thông tin video -->
								<div class="col-6">
									<div class="mb-3">
										<label for="url" class="form-label">YouTube URL</label> <input
											type="text" name="url" value="${video.url}"
											class="form-control" id="url" oninput="updatePosterPreview()">
									</div>

									<div class="mb-3">
										<label for="id" class="form-label">YouTube ID</label> <input
											type="text" name="id" value="${video.id}"
											class="form-control" id="id" readonly>
									</div>

									<div class="mb-3">
										<label for="title" class="form-label">Video Title</label> <input
											type="text" name="title" value="${video.title}"
											class="form-control" id="title">
									</div>

									<div class="mb-3">
										<label for="views" class="form-label">View Count</label> <input
											type="number" name="views" value="${video.views}"
											class="form-control" id="views">
									</div>

									<div class="mb-3">
										<label for="active" class="form-label">Status</label>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="active"
												id="active1" value="true" ${video.active ? 'checked' : ''}>
											<label class="form-check-label" for="active1">Active</label>
										</div>
										<div class="form-check">
											<input class="form-check-input" type="radio" name="active"
												id="active2" value="false" ${!video.active ? 'checked' : ''}>
											<label class="form-check-label" for="active2">Inactive</label>
										</div>
									</div>

									<div class="mb-3">
										<label for="description" class="form-label">Description</label>
										<textarea name="description" class="form-control"
											id="description" rows="3">${video.description}</textarea>
									</div>
								</div>
							</div>

							<script>
								// Tự động cập nhật poster preview từ URL YouTube
								function updatePosterPreview() {
									const urlInput = document
											.getElementById('url').value;
									const posterPreview = document
											.getElementById('posterPreview');
									const youtubeId = extractYoutubeId(urlInput);

									if (youtubeId) {
										posterPreview.src = 'https://img.youtube.com/vi/'
												+ youtubeId
												+ '/maxresdefault.jpg';
										document.getElementById('id').value = youtubeId; 
									} else {
										posterPreview.src = '';
										document.getElementById('id').value = '';
									}
								}

								// Hàm trích xuất YouTube ID từ URL
								function extractYoutubeId(url) {
									if (url.includes("youtu.be/")) {
										return url.split("youtu.be/")[1]
												.split("?")[0];
									} else if (url
											.includes("youtube.com/watch?v=")) {
										return url.split("v=")[1].split("&")[0];
									} 
									return null; 
								}
							</script>

							<!-- Các nút hành động -->
							<div class="mt-5 d-flex flex-row-reverse">
								<button type="submit"
									formaction="<c:url value='/video/reset' />"
									class="btn btn-secondary me-2">Reset</button>
								<button type="submit" id="capnhat"
									formaction="<c:url value='/video/update' />"
									class="btn btn-primary me-2">Update</button>
								<button type="submit" id="them11"
									formaction="<c:url value='/video/create' />"
									class="btn btn-success me-2">Create</button>
							</div>
						</form>

					</div>
				</div>
				<div class="tab-pane fade" id="list" role="tabpanel"
					aria-labelledby="list-tab">
					<table class="table table-striped">
						<thead>
							<tr>
								<th scope="col">Youtube ID</th>
								<th scope="col">Video Title</th>
								<th scope="col">Description</th>
								<th scope="col">View Count</th>
								<th scope="col">Poster</th>
								<th scope="col">Status</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="v" items="${videos}">
								<tr>
									<td>${v.id}</td>
									<td>${v.title}</td>
									<td>${v.description}</td>
									<td>${v.views}</td>
									<td><img src="${v.poster}" alt="Poster" width="100"></td>
									<td>${v.active ? 'Active' : 'Inactive'}</td>
									<td><a href="<c:url value='/video/edit/${v.id}' />"
										class="btn btn-primary btn-sm">Edit</a> <a
										href="<c:url value='/video/delete/${v.id}' />"
										class="btn btn-danger btn-sm" id = "xoa">Delete</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	</main>
	<footer>
		<!-- place footer here -->
	</footer>
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