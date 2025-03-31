<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!doctype html>
<html lang="en">

<head>
    <title>Quản trị User</title>
    <link rel="shortcut icon" href="/ASS_PD10302/uploads/Logo2.png" />
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <!-- Bootstrap CSS v5.2.1 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous" />
</head>

<body>
    <header>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="#">HoangLoi</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/ASS_PD10302/Home"> Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/ASS_PD10302/video/index"> Video</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/ASS_PD10302/user/index"> User</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"> Reports</a>
                        </li>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="container mt-5">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item" role="presentation">
                    <button class="nav-link active" id="editor-tab" data-bs-toggle="tab" data-bs-target="#editor"
                        type="button" role="tab" aria-controls="editor" aria-selected="true">User Editor</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="list-tab" data-bs-toggle="tab" data-bs-target="#list" type="button"
                        role="tab" aria-controls="list" aria-selected="false">User List</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="editor" role="tabpanel" aria-labelledby="editor-tab">
                <c:url var="url" value="/user"/>
                <form method="post">
                    <div class="row mt-3">
                        <div class="col-6">
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">User name</label>
                                <input type="text" class="form-control" id="username" name = "username"value = "${user.id }">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Password</label>
                                <input type="password" class="form-control" id="password" name = "password" value = "${user.password }">
                            </div>
                        </div>
                        <di class="col-6">
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Full name</label>
                                <input type="text" class="form-control" id="fullname" name = "fullname" value = "${user.fullname }">
                            </div>
                            <div class="mb-3">
                                <label for="exampleFormControlInput1" class="form-label">Email Address</label>
                                <input type="email" class="form-control" id="email" name = "email" value = "${user.email }">
                            </div>
                    </div>
                    <div class="mt-5 d-flex flex-row-reverse ">
                        <button type="submit" class="btn btn-primary me-2" formaction="${url}/delete?id=${user.id}">Delete</button>
                        <button type="submit" class="btn btn-primary me-2"formaction="${url}/update?id=${user.id}">Update</button>
                    </div>
                    </form>
                </div>
            </div>
            <div class="tab-pane fade" id="list" role="tabpanel" aria-labelledby="list-tab">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">Username</th>
                            <th scope="col">Password</th>
                            <th scope="col">FullName</th>
                            <th scope="col">Email</th>
                            <th scope="col">Role</th>
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach  var = "u" items = "${users}">
                        <tr>
                            <th scope="row">${u.id }</th>
                            <td>${u.password }</td>
                            <td>${u.fullname }</td>
                            <td>${u.email }</td>
                            <td>${u.admin ? 'admin' : 'user' }</td>
                            <td ><a href="${url}/edit/${u.id}" class="btn btn-info">Edit</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>


        </div>

    </main>
    <footer>
        <!-- place footer here -->
    </footer>
    <!-- Bootstrap JavaScript Libraries -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
        integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
</body>

</html>