import { type NextRequest, NextResponse } from "next/server";

const BACKEND_URL = process.env.BACKEND_URL || "http://app:8080";

async function handler(
	req: NextRequest,
	{ params }: { params: Promise<{ path: string[] }> },
) {
	const { path } = await params;
	const target = `${BACKEND_URL}/api/${path.join("/")}${req.nextUrl.search}`;

	// Only forward headers the backend needs — strip all browser-specific
	// headers (sec-fetch-*, cookie, origin, etc.) to avoid CORS/session issues
	const headers = new Headers();
	const auth = req.headers.get("authorization");
	if (auth) headers.set("authorization", auth);
	const ct = req.headers.get("content-type");
	if (ct) headers.set("content-type", ct);
	const accept = req.headers.get("accept");
	if (accept) headers.set("accept", accept);

	try {
		const res = await fetch(target, {
			method: req.method,
			headers,
			body: req.body,
			// @ts-expect-error -- duplex is required for streaming body
			duplex: "half",
		});

		const responseHeaders = new Headers(res.headers);
		// Remove transfer-encoding as Next.js handles it
		responseHeaders.delete("transfer-encoding");

		return new NextResponse(res.body, {
			status: res.status,
			statusText: res.statusText,
			headers: responseHeaders,
		});
	} catch (err) {
		console.error("[api-proxy] Error proxying to backend:", target, err);
		return NextResponse.json(
			{ message: "Backend unavailable" },
			{ status: 502 },
		);
	}
}

export const GET = handler;
export const POST = handler;
export const PUT = handler;
export const PATCH = handler;
export const DELETE = handler;
