/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.BackendTubes.Kos;

/**
 *
 * @author LENOVO
 */
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.Jwts;

import java.io.IOException;

//@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        if (path.startsWith("/api/kos/")) {
            filterChain.doFilter(request, response); // skip jwt check for /api/auth/**
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);
        try {
            // validasi token
            Jwts.parserBuilder()
                    .setSigningKey(jwtUtil.getKey())
                    .build()
                    .parseClaimsJws(token); // valid -> lanjut

            // kamu bisa tambah validasi user di context kalau mau
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid or expired token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
