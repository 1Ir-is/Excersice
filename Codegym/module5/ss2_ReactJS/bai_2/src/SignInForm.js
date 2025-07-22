import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";

function SignInForm() {
  return (
    <div
      className="container d-flex flex-column align-items-center justify-content-center"
      style={{ minHeight: "100vh" }}
    >
      <div className="mb-4">
        <img
          src="https://getbootstrap.com/docs/5.0/assets/brand/bootstrap-logo.svg"
          alt="Bootstrap Logo"
          width="72"
          height="72"
        />
      </div>
      <h2 className="mb-3">Please sign in</h2>
      <form className="w-100" style={{ maxWidth: 330 }}>
        <div className="form-floating mb-2">
          <input
            type="email"
            className="form-control"
            id="floatingInput"
            placeholder="name@example.com"
          />
          <label htmlFor="floatingInput">Email address</label>
        </div>
        <div className="form-floating mb-2">
          <input
            type="password"
            className="form-control"
            id="floatingPassword"
            placeholder="Password"
          />
          <label htmlFor="floatingPassword">Password</label>
        </div>
        <div className="checkbox mb-3">
          <label>
            <input type="checkbox" value="remember-me" /> Remember me
          </label>
        </div>
        <button className="w-100 btn btn-lg btn-primary" type="submit">
          Sign in
        </button>
        <p className="mt-5 mb-3 text-muted text-center">© 2017–2021</p>
      </form>
    </div>
  );
}

export default SignInForm;
