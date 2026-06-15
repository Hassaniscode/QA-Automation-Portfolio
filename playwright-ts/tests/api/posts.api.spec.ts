import { test, expect } from '@playwright/test';
import { URLS, API_TEST_DATA } from '../utils/constants';

const BASE_URL = URLS.api;

test.describe('Posts API', () => {
  test('GET /posts - should return all posts', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBe(100);
    expect(body[0]).toHaveProperty('userId');
    expect(body[0]).toHaveProperty('id');
    expect(body[0]).toHaveProperty('title');
    expect(body[0]).toHaveProperty('body');
  });

  test('GET /posts?userId=1 - should filter posts by user', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts`, {
      params: { userId: 1 },
    });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBeGreaterThan(0);
    body.forEach((post: { userId: number }) => {
      expect(post.userId).toBe(1);
    });
  });

  test('GET /posts/:id/comments - should return comments for a post', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/posts/1/comments`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBeGreaterThan(0);
    expect(body[0]).toHaveProperty('postId');
    expect(body[0]).toHaveProperty('email');
    expect(body[0]).toHaveProperty('body');
    expect(body[0].postId).toBe(1);
  });

  test('POST /posts - should create a new post', async ({ request }) => {
    const newPost = API_TEST_DATA.posts.create;
    const response = await request.post(`${BASE_URL}/posts`, { data: newPost });

    expect(response.status()).toBe(201);
    const body = await response.json();
    expect(body.title).toBe(newPost.title);
    expect(body.userId).toBe(newPost.userId);
    expect(body.id).toBeTruthy();
  });

  test('PUT /posts/:id - should replace a post', async ({ request }) => {
    const updatedPost = API_TEST_DATA.posts.update;
    const response = await request.put(`${BASE_URL}/posts/1`, { data: updatedPost });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.title).toBe(updatedPost.title);
    expect(body.body).toBe(updatedPost.body);
  });

  test('DELETE /posts/:id - should delete a post', async ({ request }) => {
    const response = await request.delete(`${BASE_URL}/posts/1`);

    expect(response.status()).toBe(200);
  });
});
