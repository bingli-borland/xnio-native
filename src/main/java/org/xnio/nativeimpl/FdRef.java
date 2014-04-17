/*
 * JBoss, Home of Professional Open Source
 *
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xnio.nativeimpl;


import org.xnio.AutomaticReference;

/**
 * @author <a href="mailto:david.lloyd@redhat.com">David M. Lloyd</a>
 */
final class FdRef<T> extends AutomaticReference<T> {

    private static final Object PERMIT = AutomaticReference.getPermit();

    final int fd;

    FdRef(final T referent, final int fd) {
        super(referent, PERMIT);
        this.fd = fd;
    }

    protected void free() {
        Log.log.tracef("Freeing %s", this);
        Native.close(fd, null);
    }

    public String toString() {
        return "file descriptor " + fd;
    }
}
