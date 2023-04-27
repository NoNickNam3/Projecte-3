<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Validation\ValidationException;

class ApiAuthController extends Controller
{
    public function login(Request $request)
    {
        $request->validate([
            'email' => 'required|string|email',
            'password' => 'required|string',
        ]);

        if (!Auth::attempt($request->only('email', 'password'))) {
            throw ValidationException::withMessages([
                'email' => trans('auth.failed'),
            ]);
        }

        $token = Auth::user()->createToken('auth_token')->plainTextToken;
        return response()->json(['token' => $token, 'user' => Auth::user()], 200);
    }

    public function logout(Request $request)
    {
        Auth::user()->tokens()->delete();
        return response()->json(['message' => 'Logged out successfully'], 200);
    }
}
