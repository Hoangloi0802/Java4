<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <title>Title</title>
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
                            <a class="nav-link active" aria-current="page" href="#"> Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"> Video</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#"> User</a>
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
                        type="button" role="tab" aria-controls="editor" aria-selected="true">Favorites</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="list-tab" data-bs-toggle="tab" data-bs-target="#list" type="button"
                        role="tab" aria-controls="list" aria-selected="false">Favorites User</button>
                </li>
                <li class="nav-item" role="presentation">
                    <button class="nav-link" id="list-tab" data-bs-toggle="tab" data-bs-target="#list-1" type="button"
                        role="tab" aria-controls="list" aria-selected="false">Favorites Friends</button>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="editor" role="tabpanel" aria-labelledby="editor-tab">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Video Title</th>
                                <th scope="col">Favorite Count</th>
                                <th scope="col">latest Date</th>
                                <th scope="col">Oldrest Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Yte_13213</th>
                                <td>Datle</td>
                                <td>105</td>
                                <td>Active</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="list" role="tabpanel" aria-labelledby="list-tab">
                    <Label>Video title</Label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Username</th>
                                <th scope="col">FullName</th>
                                <th scope="col">Email</th>
                                <th scope="col">Favorite Date</th>

                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Yte_13213</th>
                                <td>Datle</td>
                                <td>105</td>
                                <td>Active</td>
                            </tr>

                        </tbody>
                    </table>
                </div>
                <div class="tab-pane fade" id="list-1" role="tabpanel" aria-labelledby="list-tab">
                    <Label>Video title</Label>
                    <select class="form-select" aria-label="Default select example">
                        <option selected>Open this select menu</option>
                        <option value="1">One</option>
                        <option value="2">Two</option>
                        <option value="3">Three</option>
                    </select>
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">Sender Name</th>
                                <th scope="col">Sender Email</th>
                                <th scope="col">Receiver Email</th>
                                <th scope="col">Sent Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">Yte_13213</th>
                                <td>Datle</td>
                                <td>105</td>
                                <td>Active</td>
                            </tr>

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