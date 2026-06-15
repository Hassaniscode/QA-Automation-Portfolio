import { test, expect } from '@playwright/test';
import { URLS, API_TEST_DATA } from '../utils/constants';

const BASE_URL = URLS.api;

test.describe('Users API', () => {
  test('GET /users - should return list of users', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/users`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.length).toBe(10);
    expect(body[0]).toHaveProperty('id');
    expect(body[0]).toHaveProperty('name');
    expect(body[0]).toHaveProperty('email');
    expect(body[0]).toHaveProperty('username');
  });

  test('GET /users/:id - should return a single user', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/users/1`);

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.id).toBe(1);
    expect(body.name).toBeTruthy();
    expect(body.email).toBeTruthy();
  });

  test('GET /users/:id - should return 404 for non-existent user', async ({ request }) => {
    const response = await request.get(`${BASE_URL}/users/9999`);

    expect(response.status()).toBe(404);
  });

  test('POST /users - should create a new user', async ({ request }) => {
    const newUser = API_TEST_DATA.users.create;
    const response = await request.post(`${BASE_URL}/users`, { data: newUser });

    expect(response.status()).toBe(201);
    const body = await response.json();
    expect(body.name).toBe(newUser.name);
    expect(body.username).toBe(newUser.username);
    expect(body.email).toBe(newUser.email);
    expect(body.id).toBeTruthy();
  });

  test('PUT /users/:id - should update a user', async ({ request }) => {
    const updatedUser = API_TEST_DATA.users.update;
    const response = await request.put(`${BASE_URL}/users/1`, { data: updatedUser });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.name).toBe(updatedUser.name);
  });

  test('PATCH /users/:id - should partially update a user', async ({ request }) => {
    const patchData = API_TEST_DATA.users.patch;
    const response = await request.patch(`${BASE_URL}/users/1`, { data: patchData });

    expect(response.status()).toBe(200);
    const body = await response.json();
    expect(body.name).toBe(patchData.name);
  });

  test('DELETE /users/:id - should delete a user', async ({ request }) => {
    const response = await request.delete(`${BASE_URL}/users/1`);

    expect(response.status()).toBe(200);
  });
});
